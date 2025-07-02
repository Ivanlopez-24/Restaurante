package modelo.chain;

import modelo.Pedido;

/**
 * Handler abstracto para la cadena de responsabilidad en pedidos.
 */
public abstract class HandlerPedido {
    protected HandlerPedido siguiente;
    protected StringBuilder mensajes = new StringBuilder();

    public void setSiguiente(HandlerPedido siguiente) {
        this.siguiente = siguiente;
    }

    public void manejar(Pedido pedido) {
        agregarMensaje("Iniciando validación...");
        if (siguiente != null) {
            siguiente.manejar(pedido);
        }
    }

    public String getMensajes() {
        return mensajes.toString();
    }

    protected void agregarMensaje(String mensaje) {
        mensajes.append(mensaje).append("\\n");
    }
}
