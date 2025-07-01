/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.List;

public class Combo implements IItemMenu {
    private List<IItemMenu> items = new ArrayList<>();

    public void agregarItem(IItemMenu item){
        items.add(item);
    }

    @Override
    public double getPrecio(){
        return items.stream()
                    .mapToDouble(IItemMenu::getPrecio)
                    .sum();
    }

    @Override
    public String descripcion(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++){
            sb.append(items.get(i).descripcion());
            if (i < items.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<IItemMenu> getItems(){
        return new ArrayList<>(items);
    }
    @Override
public String getNombre() {
    StringBuilder sb = new StringBuilder("Combo: ");
    for (int i = 0; i < items.size(); i++) {
        sb.append(items.get(i).getNombre());
        if (i < items.size() - 1) {
            sb.append(", ");
        }
    }
    return sb.toString();
}
}
