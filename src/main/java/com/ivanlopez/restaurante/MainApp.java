// MainApp.java - Clase principal para integrar Modelo, Vista y Controlador
package com.ivanlopez.restaurante;

import modelo.fachada.FacadeRestaurante;
import modelo.chain.HandlerPedido;
import modelo.chain.ValidarStockHandler;
import modelo.chain.ValidarPagoHandler;
import modelo.mediator.MediatorRestaurante;
import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;
import modelo.MenuCompuesto;
import modelo.MenuSimple;
import modelo.Producto;
import modelo.ProductoConcreto;
import modelo.bridge.MenuAbstraccion;
import modelo.bridge.MenuImplementacion;
import vista.PedidoView;
import controlador.PedidoController;
import controlador.PagoController;
import vista.PagoView;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear fachada para manejar pedidos
            FacadeRestaurante facade = new FacadeRestaurante();

            // Crear menús usando Composite
            MenuSimple pizza = new MenuSimple("Pizza", 8.5);
            MenuSimple pasta = new MenuSimple("Pasta", 7.0);
            MenuCompuesto menuEspecial = new MenuCompuesto("Menú Especial");
            menuEspecial.agregarItem(pizza);
            menuEspecial.agregarItem(pasta);

            // Crear implementación concreta para Bridge
            Producto pizzaProducto = new ProductoConcreto("Pizza", 8.5);
            Producto pastaProducto = new ProductoConcreto("Pasta", 7.0);
            MenuImplementacion implementacion = new MenuImplementacion() {
                @Override
                public java.util.List<Producto> obtenerProductos() {
                    return java.util.Arrays.asList(pizzaProducto, pastaProducto);
                }
            };
            MenuAbstraccion menuBridge = new MenuAbstraccion(implementacion) {
                @Override
                public java.util.List<Producto> obtenerProductos() {
                    return implementacion.obtenerProductos();
                }
            };

            // Agregar items al pedido a través de la fachada
            facade.agregarMenuCompuestoAlPedido(menuEspecial);

            // Inicializar cadena de responsabilidad para validaciones
            HandlerPedido validarStock = new ValidarStockHandler();
            HandlerPedido validarPago = new ValidarPagoHandler();
            validarStock.setSiguiente(validarPago);

            // Crear instancia de Mediator para coordinar eventos
            MediatorRestaurante mediator = new MediatorRestaurante(null, null);

            // Configurar Adapter para pagos
            IPago pago = new AdaptadorPago();

            // Crear modelo y vista usando fachada y patrones
            PedidoView vista = new PedidoView(menuEspecial.getItems());

            // Crear controlador para la vista y fachada
            PedidoController controlador = new PedidoController(facade, vista, menuEspecial.getItems());

            vista.setVisible(true);

            // Agregar listener para terminar pedido y abrir vista de pago
            controlador.getView().agregarListenerTerminarPedido(e -> {
                double total = facade.calcularTotal();
                PagoView pagoView = new PagoView(total);
                PagoController pagoController = new PagoController(facade, pagoView);
                pagoView.setVisible(true);
                vista.dispose();
            });

            // Ejemplo de uso de cadena de responsabilidad y mediator
            validarStock.manejar(null); // Pasar pedido real en implementación completa
            mediator.notificar("pedidoRealizado");
            pago.pagar(15.0);
        });
    }
}
