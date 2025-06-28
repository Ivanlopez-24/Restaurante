package modelo;

public class SinDescuento implements IDescuentoStrategy {
    @Override
    public double calcularDescuento(Pedido pedido) {
        return 0;
    }
}