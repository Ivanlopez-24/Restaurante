// MainApp.java - Clase principal para integrar Modelo, Vista y Controlador

import javax.swing.SwingUtilities;

import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;
import modelo.bridge.MenuAbstraccion;
import modelo.bridge.MenuImplementacion;
import modelo.chain.HandlerPedido;
import modelo.chain.ValidarPagoHandler;
import modelo.chain.ValidarStockHandler;
import modelo.fachada.FacadeRestaurante;
import modelo.mediator.MediatorRestaurante;
import vista.PedidoView;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear fachada para manejar pedidos
            FacadeRestaurante facade = new FacadeRestaurante();

            // Crear menús usando Composite
            modelo.IItemMenu pizza = new modelo.MenuSimple("Pizza", 8.5);
            modelo.IItemMenu pasta = new modelo.MenuSimple("Pasta", 7.0);
            modelo.MenuCompuesto menuEspecial = new modelo.MenuCompuesto("Menú Especial");
            menuEspecial.agregarItem(pizza);
            menuEspecial.agregarItem(pasta);

            // Crear implementación concreta para Bridge
            modelo.Producto pizzaProducto = new modelo.ProductoConcreto("Pizza", 8.5);
            modelo.Producto pastaProducto = new modelo.ProductoConcreto("Pasta", 7.0);
            MenuImplementacion implementacion = new MenuImplementacion() {
                @Override
                public java.util.List<modelo.Producto> obtenerProductos() {
                    return java.util.Arrays.asList(pizzaProducto, pastaProducto);
                }
            };
            MenuAbstraccion menuBridge = new MenuAbstraccion(implementacion) {
                @Override
                public java.util.List<modelo.Producto> obtenerProductos() {
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

            vista.setVisible(true);

            // Ejemplo de uso de cadena de responsabilidad y mediator
            validarStock.manejar(null); // Pasar pedido real en implementación completa
            mediator.notificar("pedidoRealizado");
            pago.pagar(15.0);
        });
    }
}
