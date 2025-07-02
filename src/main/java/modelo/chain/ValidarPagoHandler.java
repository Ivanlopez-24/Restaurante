package modelo.chain;

import modelo.Pedido;

/**
 * Manejador para validar pago en la cadena de responsabilidad.
 */
public class ValidarPagoHandler extends HandlerPedido {
    @Override
    public void manejar(Pedido pedido) {
        agregarMensaje("Validando pago para el pedido...");
        // Si pasa la validación, pasa al siguiente manejador
        if (siguiente != null) {
            siguiente.manejar(pedido);
            agregarMensaje(siguiente.getMensajes());
        }
    }
}
