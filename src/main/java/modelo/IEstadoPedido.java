package modelo;

public interface IEstadoPedido {
    void manejar(Pedido pedido);
    String getEstado();
}