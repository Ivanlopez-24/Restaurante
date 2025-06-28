package modelo;

public class EnPreparacionEstado implements IEstadoPedido {
    @Override
    public void manejar(Pedido pedido) {
        pedido.setEstado(new ServidoEstado());
    }

    @Override
    public String getEstado() {
        return "En Preparación";
    }
}