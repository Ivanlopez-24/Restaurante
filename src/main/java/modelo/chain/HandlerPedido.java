package modelo.chain;

import modelo.Pedido;

/**
 * Handler abstracto para la cadena de responsabilidad en pedidos.
 */
public abstract class HandlerPedido {
    protected HandlerPedido siguiente;

    public void setSiguiente(HandlerPedido siguiente) {
        this.siguiente = siguiente;
    }

    public void manejar(Pedido pedido) {
        if (siguiente != null) {
            siguiente.manejar(pedido);
        }
    }
}
