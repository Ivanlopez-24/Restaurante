package modelo.fachada;

import java.util.List;

import modelo.Combo;
import modelo.MenuCompuesto;
import modelo.MenuSimple;
import modelo.Pedido;

/**
 * Fachada para simplificar la interacción con el sistema del restaurante.
 */
public class FacadeRestaurante {
    private Pedido pedidoActual;
    private modelo.IAccesoDatos accesoDatos;

    public FacadeRestaurante() {
        pedidoActual = new Pedido();
        accesoDatos = new modelo.ProxyAccesoDatos();
        accesoDatos.abrirArchivo("datos.txt"); // Ajustar ruta según necesidad
    }

    public void agregarItemAlPedido(MenuSimple item) {
        pedidoActual.agregarItem(item);
    }

    public void reiniciarPedido() {
        pedidoActual = new Pedido();
    }

    public void agregarMenuCompuestoAlPedido(MenuCompuesto menu) {
        pedidoActual.agregarItem(menu);
    }

    public void agregarComboAlPedido(Combo combo) {
        pedidoActual.agregarItem(combo);
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

    public Pedido obtenerPedidoActual() {
        return pedidoActual;
    }

    // Métodos para delegar acceso a datos a través del proxy
    public String leerDatos() {
        return accesoDatos.leerDatos();
    }

    public void escribirDatos(String datos) {
        accesoDatos.escribirDatos(datos);
    }

    public void cerrarAccesoDatos() {
        accesoDatos.cerrarArchivo();
    }
}
