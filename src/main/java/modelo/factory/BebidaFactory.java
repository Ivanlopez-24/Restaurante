package modelo.factory;

import modelo.Bebida;
import modelo.IItemMenu;

public class BebidaFactory extends ProductoFactory {
    @Override
    public IItemMenu crearProducto(String nombre, double precio) {
        return new Bebida(nombre, precio);
    }
}