package controller;

import factory.ProductoFactory;
import model.Producto;

public class MenuController {
    private ProductoFactory productoFactory;

    public MenuController(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
    }

    public Producto crearProducto(String nombre, double precio) {
        return productoFactory.crearProducto(nombre, precio);
    }
}
