package modelo.template;

import java.util.ArrayList;
import java.util.List;

import modelo.Ingrediente;

/**
 * Proceso de preparación para platos mexicanos.
 */
public class ProcesoPreparacionMexicana extends ProcesoPreparacion {
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public ProcesoPreparacionMexicana() {
        ingredientes.add(new Ingrediente("Chile"));
        ingredientes.add(new Ingrediente("Maíz"));
        ingredientes.add(new Ingrediente("Frijoles"));
    }

    @Override
    protected void prepararIngredientes() {
        System.out.println("Preparando ingredientes mexicanos:");
        for (Ingrediente ingrediente : ingredientes) {
            System.out.println("- " + ingrediente.getNombre());
        }
    }

    @Override
    protected void cocinar() {
        System.out.println("Cocinando plato mexicano...");
    }
}
