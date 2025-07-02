package modelo.mediator;

import modelo.Mesa;
import modelo.Pedido;

/**
 * Mediator para gestionar la comunicación en el restaurante.
 */
public class MediatorRestaurante {
    private Mesa mesa;
    private Pedido pedido;

    public MediatorRestaurante(Mesa mesa, Pedido pedido) {
        this.mesa = mesa;
        this.pedido = pedido;
    }

    public void notificar(String evento) {
        switch (evento) {
            case "pedidoRealizado":
                System.out.println("Mediator: Pedido realizado, notificando a cocina.");
                // lógica para notificar a cocina
                break;
            case "pedidoListo":
                System.out.println("Mediator: Pedido listo, notificando a servicio.");
                // lógica para notificar a servicio
                break;
            default:
                System.out.println("Mediator: Evento desconocido.");
        }
    }
}
