package modelo;

/**
 * Clase concreta que extiende Producto para uso en ejemplos.
 */
public class ProductoConcreto extends Producto {
    public ProductoConcreto(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String descripcion() {
        return getNombre() + " - Precio: " + getPrecio();
    }
}
