package factory;

import modelo.IItemMenu;
import modelo.Bebida;

public class BebidaFactory extends ProductoFactory {
    @Override
    public IItemMenu crearProducto(String nombre, double precio) {
        return new Bebida(nombre, precio);
    }
}