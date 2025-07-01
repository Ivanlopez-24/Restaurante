package factory;

import modelo.IItemMenu;
import modelo.Plato;

public class PlatoFactory extends ProductoFactory {
    @Override
    public IItemMenu crearProducto(String nombre, double precio) {
        return (IItemMenu) new Plato(nombre, precio);
    }
}