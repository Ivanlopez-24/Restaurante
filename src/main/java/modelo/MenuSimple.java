package modelo;

import java.util.ArrayList;
import java.util.List;

public class MenuSimple implements IItemMenu {
    private String nombre;
    private double precio;
    private List<IItemMenu> items;

    public MenuSimple(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.items = new ArrayList<>();
    }

    public void aceptar(modelo.visitor.Visitor visitor) {
        visitor.visitarMenuSimple(this);
    }

    public void agregarItem(IItemMenu item) {
        items.add(item);
    }

    public List<IItemMenu> getItems() {
        return items;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        double total = precio;
        for (IItemMenu item : items) {
            total += item.getPrecio();
        }
        return total;
    }

    @Override
    public String descripcion() {
        StringBuilder sb = new StringBuilder(nombre + " - Precio base: " + precio);
        if (!items.isEmpty()) {
            sb.append("\n  Incluye:\n");
            for (IItemMenu item : items) {
                sb.append("   * ").append(item.descripcion()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return descripcion();
    }
}
