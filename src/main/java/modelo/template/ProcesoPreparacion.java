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
        modelo.Salida.println("Sirviendo el plato.");
    }
}
