package controlador;

import factory.ProductoFactory;
import modelo.Producto;

public class MenuController {
    private ProductoFactory productoFactory;

    public MenuController(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
    }

    public Producto crearProducto(String nombre, double precio) {
        return (Producto) productoFactory.crearProducto(nombre, precio);
    }
}
