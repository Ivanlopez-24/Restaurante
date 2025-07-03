package adaptador;

/**
 * Adaptador para convertir valores monetarios a pesos colombianos.
 */
public class AdaptadorMoneda {
    private static final double TASA_CONVERSION_COP = 4000.0; // Ejemplo tasa USD a COP

    public double convertirADolaresAPesos(double montoUSD) {
        return montoUSD * TASA_CONVERSION_COP;
    }
}
