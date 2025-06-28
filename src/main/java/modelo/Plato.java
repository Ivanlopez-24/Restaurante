package modelo;

public class Plato extends Producto {
    public Plato(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String descripcion() {
        return "Plato: " + nombre + ", Precio: " + precio;
    }
}