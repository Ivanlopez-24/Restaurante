package modelo.template;

/**
 * Clase abstracta que define el esqueleto del proceso de preparación.
 */
public abstract class ProcesoPreparacion {
    // Método plantilla
    public final void preparar() {
        prepararIngredientes();
        cocinar();
        servir();
    }

    protected abstract void prepararIngredientes();

    protected abstract void cocinar();

    protected void servir() {
        System.out.println("Sirviendo el plato.");
    }
}
