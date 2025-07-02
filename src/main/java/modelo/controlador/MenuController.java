package modelo.controlador;

import modelo.Producto;
import modelo.factory.ProductoFactory;

public class MenuController {
    private ProductoFactory productoFactory;

    public MenuController(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
    }

    public Producto crearProducto(String nombre, double precio) {
        return (Producto) productoFactory.crearProducto(nombre, precio);
    }
}
