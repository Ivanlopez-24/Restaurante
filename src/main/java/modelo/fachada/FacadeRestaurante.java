package modelo.fachada;

import java.util.List;

import modelo.MenuCompuesto;
import modelo.MenuSimple;
import modelo.Pedido;

/**
 * Fachada para simplificar la interacción con el sistema del restaurante.
 */
public class FacadeRestaurante {
    private Pedido pedidoActual;

    public FacadeRestaurante() {
        pedidoActual = new Pedido();
    }

    public void agregarItemAlPedido(MenuSimple item) {
        pedidoActual.agregarItem(item);
    }

    public void agregarMenuCompuestoAlPedido(MenuCompuesto menu) {
        pedidoActual.agregarItem(menu);
    }

    public double calcularTotal() {
        return pedidoActual.getPrecioTotal();
    }

    public void realizarPedido() {
        pedidoActual.ejecutar();
    }

    public List<?> obtenerItemsPedido() {
        return pedidoActual.getItems();
    }
}
