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

import modelo.mediator.MediatorRestaurante;

public class PagoController {
    private FacadeRestaurante facade;
    private PagoView view;
    private IPago pagoAdapter;
    private PedidoView pedidoView;
    private PedidoController pedidoController;
    private MediatorRestaurante mediator;

    public PagoController(FacadeRestaurante facade, PagoView view, PedidoView pedidoView, PedidoController pedidoController) {
        this.facade = facade;
        this.view = view;
        this.pagoAdapter = new AdaptadorPago();
        this.pedidoView = pedidoView;
        this.pedidoController = pedidoController;
        this.mediator = new MediatorRestaurante(null, facade.obtenerPedidoActual());

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

                // Validar stock y pago usando cadena de responsabilidad
                modelo.chain.ValidarStockHandler validarStock = new modelo.chain.ValidarStockHandler();
                modelo.chain.ValidarPagoHandler validarPago = new modelo.chain.ValidarPagoHandler();
                validarStock.setSiguiente(validarPago);
                validarStock.manejar(facade.obtenerPedidoActual());

                // Mostrar ventana "Validando stock"
                javax.swing.JOptionPane.showMessageDialog(view, "Validando stock para el pedido...");
                Thread.sleep(1000);

                // Realizar pago
                pagoAdapter.pagar(monto);

                // Mostrar ventana "Validando pago"
                javax.swing.JOptionPane.showMessageDialog(view, "Validando pago para el pedido...");
                Thread.sleep(1000);

                // Mostrar mensaje de éxito y devolver cambio si hay
                double cambio = monto - total;
                String mensaje = "Pago realizado con éxito. Gracias por su compra.";
                if (cambio > 0) {
                    mensaje += String.format("\nSu cambio es: $%.2f", cambio);
                }
                javax.swing.JOptionPane.showMessageDialog(view, mensaje);

                // Notificar a cocina que el pedido fue realizado
                mediator.notificar("pedidoRealizado");

                // Simular preparación del plato con retardo
                new Thread(() -> {
                    try {
                        Thread.sleep(3000); // Simula tiempo de preparación
                        mediator.notificar("pedidoListo");
                        // Mostrar plato preparado (puede ser un diálogo o actualización en UI)
                            javax.swing.SwingUtilities.invokeLater(() -> {
                                javax.swing.JOptionPane.showMessageDialog(view, "Plato preparado y listo para servir.");
                                // Después de mostrar el mensaje, cerrar vista de pago y volver a selección de menú
                                view.setVisible(false);
                                pedidoView.setVisible(false);
                                // Limpiar pedido y actualizar vista para nuevo pedido
                                pedidoController.limpiarPedidoYActualizarVista();
                                // Mostrar detalle del pedido anterior
                                pedidoController.mostrarPedidoAnterior();
                                com.ivanlopez.restaurante.MainApp.iniciarAplicacion();
                            });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();

                // Ocultar vista de pago mientras se prepara el plato
                view.setVisible(false);

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
