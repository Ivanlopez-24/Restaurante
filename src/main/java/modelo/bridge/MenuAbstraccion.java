package modelo.bridge;

import java.util.List;

import modelo.Producto;

/**
 * Abstracción para el manejo de menús.
 */
public abstract class MenuAbstraccion {
    protected MenuImplementacion implementacion;

    public MenuAbstraccion(MenuImplementacion implementacion) {
        this.implementacion = implementacion;
    }

    public abstract List<Producto> obtenerProductos();
}
