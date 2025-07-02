package modelo.factory;

import modelo.Producto;

public interface MenuFactory {
    Producto crearPlato(String nombre, double precio);
    Producto crearBebida(String nombre, double precio);
    Producto crearPostre(String nombre, double precio);
}
