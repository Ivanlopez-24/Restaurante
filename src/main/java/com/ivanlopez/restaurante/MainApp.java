package com.ivanlopez.restaurante;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controlador.PagoController;
import controlador.PedidoController;
import modelo.IItemMenu;
import modelo.IIterator;
import modelo.MenuItaliano;
import modelo.MenuMexicano;
import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;
import modelo.chain.HandlerPedido;
import modelo.chain.ValidarPagoHandler;
import modelo.chain.ValidarStockHandler;
import modelo.fachada.FacadeRestaurante;
import modelo.mediator.MediatorRestaurante;
import vista.PagoView;
import vista.PedidoView;

public class MainApp {

    private static FacadeRestaurante facade = new FacadeRestaurante();
    private static PedidoView vista;
    private static PedidoController controlador;
    private static MenuMexicano menuMexicano = new MenuMexicano("Menú Mexicano", 0, null);
    private static MenuItaliano menuItaliano = new MenuItaliano("Menú Italiano");

    public static void iniciarAplicacion() {
        if (vista == null || controlador == null) {
            // Mostrar diálogo para elegir menú
            String[] opciones = {menuMexicano.getNombre(), menuItaliano.getNombre()};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un menú:", "Menú",
                    JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

            // Determinar menú seleccionado
            if (seleccion != null && seleccion.equals(menuMexicano.getNombre())) {
                // Obtener listas desde iteradores
                List<IItemMenu> platosMexicano = new java.util.ArrayList<>();
                IIterator<IItemMenu> iterPlatosMex = menuMexicano.crearIteradorPlatos();
                while (iterPlatosMex.hasNext()) {
                    platosMexicano.add(iterPlatosMex.next());
                }
                List<IItemMenu> bebidasMexicano = new java.util.ArrayList<>();
                IIterator<IItemMenu> iterBebidasMex = menuMexicano.crearIteradorBebidas();
                while (iterBebidasMex.hasNext()) {
                    bebidasMexicano.add(iterBebidasMex.next());
                }
                vista = new PedidoView(platosMexicano, bebidasMexicano);
                controlador = new PedidoController(facade, vista, platosMexicano);
            } else if (seleccion != null && seleccion.equals(menuItaliano.getNombre())) {
                // Obtener listas desde iteradores
                List<IItemMenu> platosItaliano = new java.util.ArrayList<>();
                IIterator<IItemMenu> iterPlatosItal = menuItaliano.crearIteradorPlatos();
                while (iterPlatosItal.hasNext()) {
                    platosItaliano.add(iterPlatosItal.next());
                }
                List<IItemMenu> bebidasItaliano = new java.util.ArrayList<>();
                IIterator<IItemMenu> iterBebidasItal = menuItaliano.crearIteradorBebidas();
                while (iterBebidasItal.hasNext()) {
                    bebidasItaliano.add(iterBebidasItal.next());
                }
                vista = new PedidoView(platosItaliano, bebidasItaliano);
                controlador = new PedidoController(facade, vista, platosItaliano);
            } else {
                vista = new PedidoView(java.util.Collections.emptyList(), java.util.Collections.emptyList());
                controlador = new PedidoController(facade, vista, java.util.Collections.emptyList());
            }
        }

        controlador.mostrarPedidoAnterior(); // Mostrar pedido anterior al iniciar

        vista.setVisible(true);

        controlador.getView().agregarListenerTerminarPedido(e -> {
            double total = facade.calcularTotal();
            PagoView pagoView = new PagoView(total);
            PagoController pagoController = new PagoController(facade, pagoView, vista, controlador);
            pagoView.setVisible(true);
            vista.setVisible(false);

            // Al cerrar la vista de pago, volver a selección de menú sin duplicar interfaces
            pagoView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Ocultar vistas en lugar de destruirlas
                    vista.setVisible(false);
                    pagoView.setVisible(false);
                    // Reiniciar flujo mostrando la vista existente
                    SwingUtilities.invokeLater(() -> {
                        vista.setVisible(true);
                        controlador.mostrarPedidoAnterior();
                    });
                }
            });

        // Inicializar cadena de responsabilidad para validaciones
        HandlerPedido validarStock = new ValidarStockHandler();
        HandlerPedido validarPago = new ValidarPagoHandler();
        validarStock.setSiguiente(validarPago);

        // Crear instancia de Mediator para coordinar eventos
        MediatorRestaurante mediator = new MediatorRestaurante(null, null);

        // Configurar Adapter para pagos
        IPago pago = new AdaptadorPago();

        // Ejemplo de uso de cadena de responsabilidad y mediator
        validarStock.manejar(null); // Pasar pedido real en implementación completa
        mediator.notificar("pedidoRealizado");
        pago.pagar(15.0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            iniciarAplicacion();
        });
    }
}
