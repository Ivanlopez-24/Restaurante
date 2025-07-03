package modelo.visitor;

import modelo.MenuCompuesto;
import modelo.MenuSimple;

/**
 * Visitor concreto para calcular el precio total o generar reporte.
 */
public class VisitorCalculoPrecio implements Visitor {
    private double total = 0.0;
    private StringBuilder reporte = new StringBuilder();
    private static final double TASA_CONVERSION_COP = 4000.0; // Ejemplo tasa USD a COP

    @Override
    public void visitarMenuSimple(MenuSimple menuSimple) {
        double precioUSD = menuSimple.getPrecio();
        double precioCOP = precioUSD * TASA_CONVERSION_COP;
        total += precioCOP;
        reporte.append("MenuSimple: ").append(menuSimple.getNombre())
               .append(" - Precio (COP): ").append(String.format("%.2f", precioCOP)).append("\\n");
    }

    @Override
    public void visitarMenuCompuesto(MenuCompuesto menuCompuesto) {
        double precioUSD = menuCompuesto.getPrecio();
        double precioCOP = precioUSD * TASA_CONVERSION_COP;
        total += precioCOP;
        reporte.append("MenuCompuesto: ").append(menuCompuesto.getNombre())
               .append(" - Precio (COP): ").append(String.format("%.2f", precioCOP)).append("\\n");
    }

    public double getTotal() {
        return total;
    }

    public String getReporte() {
        return reporte.toString();
    }
}
