package factory;

import modelo.IItemMenu;

public abstract class ProductoFactory {
    public abstract IItemMenu crearProducto(String nombre, double precio);
}