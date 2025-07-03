package controlador;

import modelo.FlyweightFactory;
import modelo.IItemMenu;
import modelo.Pedido;
import vista.IngredientesView;
import vista.PedidoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import modelo.FlyweightFactory;
import modelo.Pedido;
import modelo.IItemMenu;
import modelo.fachada.FacadeRestaurante;
import vista.IngredientesView;
import vista.PedidoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class PedidoController {
    private Pedido pedido;
    private PedidoView view;
    private IngredientesView ingredientesView;
    private FlyweightFactory flyweightFactory;

    // Constructor nuevo que recibe Pedido, PedidoView y FlyweightFactory
    public PedidoController(Pedido pedido, PedidoView view, FlyweightFactory flyweightFactory) {
        this.pedido = pedido;
        this.view = view;
        this.flyweightFactory = flyweightFactory;
        this.ingredientesView = new IngredientesView(flyweightFactory);

        // Suscribir observador para actualizar vista al cambiar pedido
        modelo.observador.PedidoObservador pedidoObservador = new modelo.observador.PedidoObservador(view);
        this.pedido.agregarObservador(pedidoObservador);

        this.view.agregarListenerAgregar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IItemMenu item = PedidoController.this.view.getPlatoSeleccionado();
                if (item != null) {
                    PedidoController.this.pedido.agregarItem(item);
                    actualizarVista();
                }
            }
        });

        this.view.agregarListenerAgregarBebida(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IItemMenu bebida = PedidoController.this.view.getBebidaSeleccionada();
                if (bebida != null) {
                    PedidoController.this.pedido.agregarItem(bebida);
                    actualizarVista();
                }
            }
        });

        this.view.agregarListenerTerminarPedido(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido.guardarEstado();

                // Asignar estrategia de descuento según total
                double totalSinDescuento = pedido.getItems().stream()
                    .mapToDouble(item -> item.getPrecio())
                    .sum();

                if (totalSinDescuento > 50) {
                    pedido.setDescuento(new modelo.DescuentoPorcentaje(20));
                } else if (totalSinDescuento > 30) {
                    pedido.setDescuento(new modelo.DescuentoPorcentaje(10));
                } else {
                    pedido.setDescuento(new modelo.SinDescuento());
                }

                PedidoController.this.view.actualizarMensajes("Pedido terminado.");
                mostrarPedidoAnterior();
                // Aquí se puede agregar lógica para abrir vista de pago y luego volver a selección de menú
                PedidoController.this.view.setVisible(false);
                // Se puede notificar a MainApp o controlador principal para reiniciar flujo
            }
        });

        this.view.agregarListenerMostrarIngredientes(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarIngredientesCompartidos();
            }
        });

        // Añadir listener para botón de reporte Visitor
        this.view.agregarListenerReporteVisitor(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                modelo.visitor.VisitorCalculoPrecio visitor = new modelo.visitor.VisitorCalculoPrecio();
                // Aplicar visitor a los menús del pedido anterior
                for (IItemMenu item : pedido.obtenerItemsPedidoAnterior()) {
                    if (item instanceof modelo.MenuSimple) {
                        ((modelo.MenuSimple) item).aceptar(visitor);
                    } else if (item instanceof modelo.MenuCompuesto) {
                        ((modelo.MenuCompuesto) item).aceptar(visitor);
                    }
                }
                String reporte = visitor.getReporte();
                double totalCOP = visitor.getTotal();
                view.actualizarMensajes("Reporte Visitor:\\n" + reporte + "\\nTotal calculado (COP): $" + String.format("%.2f", totalCOP));
            }
        });
    }

    // Constructor adicional para compatibilidad con MainApp y PagoController
    public PedidoController(FacadeRestaurante facade, PedidoView view, List<IItemMenu> menuItems) {
        this(facade.obtenerPedidoActual(), view, new FlyweightFactory());
        // Aquí se puede agregar lógica para inicializar Pedido con facade y menuItems si es necesario
    }

    public PedidoView getView() {
        return this.view;
    }

    private void actualizarVista() {
        List<IItemMenu> items = pedido.getItems();
        StringBuilder sb = new StringBuilder();
        for (IItemMenu item : items) {
            sb.append(item.getNombre()).append(" - $").append(item.getPrecio()).append("\n");
        }
        view.actualizarPedido(sb.toString(), pedido.getPrecioTotal());
    }

    public void limpiarVistaPedido(FacadeRestaurante facade) {
        // Guardar estado actual antes de limpiar para preservar pedido anterior
        pedido.guardarEstado();
        // Limpiar pedido actual
        pedido.limpiarPedido();
        // facade.reiniciarPedido(); // Opcional si reinicia el pedido en fachada
        pedido = facade.obtenerPedidoActual();
        view.limpiarDetallePedido();
        mostrarPedidoAnterior(); // Mostrar pedido anterior al limpiar vista
    }

    public void mostrarPedidoAnterior() {
        if (pedido.tienePedidoAnterior()) {
            StringBuilder sb = new StringBuilder();
            for (IItemMenu item : pedido.obtenerItemsPedidoAnterior()) {
                sb.append(item.getNombre()).append(" - $").append(item.getPrecio()).append("\n");
            }
            view.actualizarPedidoAnterior(sb.toString());
        }
    }

    public void limpiarPedidoYActualizarVista() {
        pedido.limpiarPedido();
        view.limpiarDetallePedido();
        actualizarVista();
    }

    private void mostrarIngredientesCompartidos() {
        Map<String, Integer> usoIngredientes = flyweightFactory.getUsoIngredientes();
        ingredientesView.actualizarIngredientes(usoIngredientes);
        ingredientesView.setVisible(true);
    }
}
