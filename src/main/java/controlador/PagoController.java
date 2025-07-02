package controlador;

import modelo.fachada.FacadeRestaurante;
import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;
import vista.PagoView;
import vista.PedidoView;
import modelo.IItemMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PagoController {
    private FacadeRestaurante facade;
    private PagoView view;
    private IPago pagoAdapter;

    public PagoController(FacadeRestaurante facade, PagoView view) {
        this.facade = facade;
        this.view = view;
        this.pagoAdapter = new AdaptadorPago();

        this.view.agregarListenerPagar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarPago();
            }
        });
    }

    private void procesarPago() {
        String montoStr = view.getMontoIngresado();
        try {
            double monto = Double.parseDouble(montoStr);
            double total = facade.calcularTotal();
            if (monto >= total) {
                // Mostrar ventana "Validando stock"
                javax.swing.JOptionPane.showMessageDialog(view, "Validando stock para el pedido...");
                Thread.sleep(1000);
                // Mostrar ventana "Validando pago"
                javax.swing.JOptionPane.showMessageDialog(view, "Validando pago para el pedido...");
                Thread.sleep(1000);

                // Validar stock y pago usando cadena de responsabilidad
                modelo.chain.ValidarStockHandler validarStock = new modelo.chain.ValidarStockHandler();
                modelo.chain.ValidarPagoHandler validarPago = new modelo.chain.ValidarPagoHandler();
                validarStock.setSiguiente(validarPago);
                validarStock.manejar(facade.obtenerPedidoActual());

                // Realizar pago
                pagoAdapter.pagar(monto);

                // Mostrar mensaje de éxito y devolver cambio si hay
                double cambio = monto - total;
                String mensaje = "Pago realizado con éxito. Gracias por su compra.";
                if (cambio > 0) {
                    mensaje += String.format("\nSu cambio es: $%.2f", cambio);
                }
                int opcion = javax.swing.JOptionPane.showConfirmDialog(view,
                    mensaje + "\n¿Desea continuar con otro pedido?",
                    "Mensaje",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                    view.dispose();
                    @SuppressWarnings("unchecked")
                    List<IItemMenu> menuItems = (List<IItemMenu>) (List<?>) facade.obtenerItemsPedido();
                    PedidoView pedidoView = new PedidoView(menuItems);
                    PedidoController pedidoController = new PedidoController(facade, pedidoView, menuItems);
                    pedidoView.setVisible(true);
                } else {
                    System.exit(0);
                }
            } else {
                view.mostrarMensaje("Monto insuficiente para realizar el pago.");
            }
        } catch (NumberFormatException ex) {
            view.mostrarMensaje("Por favor, ingrese un monto válido.");
        } catch (InterruptedException e) {
            view.mostrarMensaje("Error en la validación. Intente nuevamente.");
        }
    }
}
