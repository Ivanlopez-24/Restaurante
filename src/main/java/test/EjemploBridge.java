package test;

import java.util.Arrays;
import java.util.List;

import modelo.Producto;
import modelo.ProductoConcreto;
import modelo.bridge.MenuAbstraccion;
import modelo.bridge.MenuImplementacion;

/**
 * Ejemplo de uso del patrón Bridge con objetos.
 */
public class EjemploBridge {

    // Implementación concreta de MenuImplementacion
        static class MenuImplementacionConcreta implements MenuImplementacion {
            @Override
            public List<Producto> obtenerProductos() {
                return Arrays.asList(
                    new ProductoConcreto("Pizza", 8.5),
                    new ProductoConcreto("Pasta", 7.0)
                );
            }
        }

    // Abstracción concreta de MenuAbstraccion
    static class MenuConcreto extends MenuAbstraccion {
        public MenuConcreto(MenuImplementacion implementacion) {
            super(implementacion);
        }

        @Override
        public List<Producto> obtenerProductos() {
            return implementacion.obtenerProductos();
        }
    }

        public void mostrarMenu() {
            MenuImplementacion implementacion = new MenuImplementacionConcreta();
            MenuAbstraccion menu = new MenuConcreto(implementacion);
            System.out.println("Productos del menú:");
            for (Producto p : menu.obtenerProductos()) {
                System.out.println("- " + p.getNombre() + ": " + p.getPrecio());
            }
        }
}
