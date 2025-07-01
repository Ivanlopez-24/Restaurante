package modelo;

public class ServidoEstado implements IEstadoPedido {

    @Override
    public void manejar(Pedido pedido) {
        System.out.println("El pedido ya fue servido.");
    }

    @Override
    public String getEstado() {
        return "Servido";
    }
}
