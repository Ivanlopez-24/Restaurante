/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ivanl
 */
public class Combo implements IItemMenu{
    private List<IItemMenu> items = new ArrayList<>();
    
    public void agregarItem(IItemMenu item){
        items.add(item);
    }
    @Override
    public double getPrecio(){
        return items.stream()
                    .mapToDouble(IItemMenu ::getPrecio)
                    .sum();
    }
    public String getDescripcion(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++){
            sb.append(items.get(i).getDescripcion());
            if (i < items.size() -1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
    public List<IItemMenu> getItems(){
        return new ArrayList<>(items);
    }
}
