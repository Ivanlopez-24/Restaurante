package modelo;

public class MenuSimple implements IItemMenu {
    private String nombre;
    private double precio;

    public MenuSimple(String nombre, double precio) {
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
        return nombre + " - Precio: " + precio;
    }

    @Override
    public String toString() {
        return descripcion();
    }
}
