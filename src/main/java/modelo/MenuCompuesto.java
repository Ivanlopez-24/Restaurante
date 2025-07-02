package modelo;

import java.util.ArrayList;
import java.util.List;

public class MenuCompuesto implements IItemMenu {
    private String nombre;
    private List<IItemMenu> items = new ArrayList<>();

    public MenuCompuesto(String nombre) {
        this.nombre = nombre;
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
        return items.stream().mapToDouble(IItemMenu::getPrecio).sum();
    }

    @Override
    public String descripcion() {
        StringBuilder sb = new StringBuilder(nombre + ":\n");
        for (IItemMenu item : items) {
            sb.append(" - ").append(item.descripcion()).append("\n");
        }
        return sb.toString();
    }
}
