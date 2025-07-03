package modelo.template;

import java.util.ArrayList;
import java.util.List;

import modelo.Ingrediente;

/**
 * Proceso de preparación para platos italianos.
 */
public class ProcesoPreparacionItaliana extends ProcesoPreparacion {
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public ProcesoPreparacionItaliana() {
        ingredientes.add(new Ingrediente("Tomate"));
        ingredientes.add(new Ingrediente("Queso"));
        ingredientes.add(new Ingrediente("Albahaca"));
    }

    @Override
    protected void prepararIngredientes() {
        modelo.Salida.println("Preparando ingredientes italianos:");
        for (Ingrediente ingrediente : ingredientes) {
            modelo.Salida.println("- " + ingrediente.getNombre());
        }
    }

    @Override
    protected void cocinar() {
        System.out.println("Cocinando plato italiano...");
    }
}
