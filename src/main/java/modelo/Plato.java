package modelo;

import java.util.ArrayList;
import java.util.List;

public class Plato extends Producto implements IItemMenu {
    private List<Ingrediente> ingredientes;

    public Plato(String nombre, double precio) {
        super(nombre, precio);
        this.ingredientes = new ArrayList<>();
    }

    public Plato(String nombre, double precio, FlyweightFactory flyweightFactory) {
        this(nombre, precio);
        if (flyweightFactory != null) {
            // Ejemplo: agregar ingredientes comunes para todos los platos, se puede personalizar luego
            ingredientes.add(flyweightFactory.obtenerIngrediente("Ingrediente base"));
        }
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
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
        StringBuilder sb = new StringBuilder("Plato: " + nombre + ", Precio: " + precio + "\nIngredientes:\n");
        for (Ingrediente ing : ingredientes) {
            sb.append(" - ").append(ing.getNombre()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
