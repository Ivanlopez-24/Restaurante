package modelo;

public class DescuentoPorcentaje implements IDescuentoStrategy {
    private double porcentaje;

    public DescuentoPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(Pedido pedido) {
        double total = pedido.getItems().stream()
                             .mapToDouble(item -> item.getPrecio())
                             .sum();
        return total * (porcentaje / 100);
    }
}