package modelo.observador;

import modelo.IObservador;
import modelo.Pedido;
import vista.PedidoView;

/**
 * Observador que actualiza la vista del pedido cuando hay cambios.
 */
public class PedidoObservador implements IObservador {
    private PedidoView pedidoView;

    public PedidoObservador(PedidoView pedidoView) {
        this.pedidoView = pedidoView;
    }

    @Override
    public void actualizar(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        pedido.getItems().forEach(item -> {
            sb.append(item.getNombre()).append(" - $").append(item.getPrecio()).append("\n");
        });
        pedidoView.actualizarPedido(sb.toString(), pedido.getPrecioTotal());
    }
}
