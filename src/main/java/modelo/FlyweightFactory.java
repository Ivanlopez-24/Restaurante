/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ivanl
 */
public class FlyweightFactory {
    private Map<String, Ingrediente> ingredientes = new HashMap<>();

    /**
     * Devuelve una instancia compartida de Ingrediente.
     * @param nombre Nombre del ingrediente.
     * @return Ingrediente compartido.
     */
    public Ingrediente obtenerIngrediente(String nombre) {
        if (!ingredientes.containsKey(nombre)) {
            ingredientes.put(nombre, new Ingrediente(nombre));
        }
        return ingredientes.get(nombre);
    }
}
