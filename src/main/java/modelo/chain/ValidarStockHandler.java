package modelo.chain;

import modelo.Pedido;

/**
 * Manejador para validar stock en la cadena de responsabilidad.
 */
public class ValidarStockHandler extends HandlerPedido {
    @Override
    public void manejar(Pedido pedido) {
        // Lógica para validar stock
        System.out.println("Validando stock para el pedido...");
        // Si pasa la validación, pasa al siguiente manejador
        if (siguiente != null) {
            siguiente.manejar(pedido);
        }
    }
}
