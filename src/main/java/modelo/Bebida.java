package modelo;

public class Bebida implements IItemMenu {
    private String nombre;
    private double precio;

    public Bebida(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public String descripcion() {
        return "Bebida: " + nombre + ", Precio: " + precio;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
