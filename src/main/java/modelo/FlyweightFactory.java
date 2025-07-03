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
import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private Map<String, Ingrediente> ingredientes = new HashMap<>();
    private Map<String, Integer> usoIngredientes = new HashMap<>();

    /**
     * Devuelve una instancia compartida de Ingrediente.
     * Incrementa el contador de uso para el ingrediente.
     * @param nombre Nombre del ingrediente.
     * @return Ingrediente compartido.
     */
    public Ingrediente obtenerIngrediente(String nombre) {
        if (!ingredientes.containsKey(nombre)) {
            ingredientes.put(nombre, new Ingrediente(nombre));
            usoIngredientes.put(nombre, 0);
        }
        usoIngredientes.put(nombre, usoIngredientes.get(nombre) + 1);
        return ingredientes.get(nombre);
    }

    /**
     * Devuelve un mapa con la cantidad de usos por ingrediente.
     * @return Mapa nombre ingrediente -> cantidad de usos.
     */
    public Map<String, Integer> getUsoIngredientes() {
        return new HashMap<>(usoIngredientes);
    }
}
