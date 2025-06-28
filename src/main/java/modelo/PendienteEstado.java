package modelo;

public class PendienteEstado implements IEstadoPedido {
    @Override
    public void manejar(Pedido pedido) {
        pedido.setEstado(new EnPreparacionEstado());
    }

    @Override
    public String getEstado() {
        return "Pendiente";
    }
}