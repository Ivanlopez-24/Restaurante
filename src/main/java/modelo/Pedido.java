/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements IAgregado<IItemMenu> {
    private List<IItemMenu> items = new ArrayList<>();
    private IEstadoPedido estado;
    private IDescuentoStrategy descuento;
    private Notificador notificador = new Notificador();

    // Para Command/Memento
    private Memento mementoPrevio;

    /** Constructor vacío */
    public Pedido() { }

    /** Builder: agrega un ítem y permite encadenar */
    public Pedido agregarItem(IItemMenu item) {
        items.add(item);
        return this;
    }

    /** Limpia el pedido para iniciar uno nuevo */
    public void limpiarPedido() {
        items.clear();
        estado = null;
        descuento = null;
    }

    /** Calcula el precio total, aplicando descuento si existe */
    public double getPrecioTotal() {
        double total = items.stream()
                            .mapToDouble(IItemMenu::getPrecio)
                            .sum();
        if (descuento != null) {
            total -= descuento.calcularDescuento(this);
        }
        return total;
    }

    /** Setter de estrategia de descuento */
    public void setDescuento(IDescuentoStrategy descuento) {
        this.descuento = descuento;
    }

    /** Obtiene copia de la lista de ítems */
    public List<IItemMenu> getItems() {
        return new ArrayList<>(items);
    }

    /** Setter de estado (State) */
    public void setEstado(IEstadoPedido estado) {
        this.estado = estado;
    }

    /** Ejecuta la acción asociada al estado */
    public void ejecutar() {
        if (estado != null) {
            estado.manejar(this);
            notificador.notificar(this);
        }
    }

    /** Suscribe un observador al pedido */
    public void agregarObservador(IObservador obs) {
        notificador.agregarObservador(obs);
    }

    // === Command: deshacer ===

    /** Guarda el estado actual en un memento */
    public void guardarEstado() {
        mementoPrevio = new Memento(new ArrayList<>(items), estado, descuento);
    }

    /** Restaura el estado previo */
    public void deshacer() {
        if (mementoPrevio != null) {
            this.items    = new ArrayList<>(mementoPrevio.items);
            this.estado   = mementoPrevio.estado;
            this.descuento= mementoPrevio.descuento;
            notificador.notificar(this);
        }
    }

    // === Memento interno ===
    private static class Memento {
        private final List<IItemMenu> items;
        private final IEstadoPedido estado;
        private final IDescuentoStrategy descuento;

        private Memento(List<IItemMenu> items,
                        IEstadoPedido estado,
                        IDescuentoStrategy descuento) {
            this.items    = items;
            this.estado   = estado;
            this.descuento= descuento;
        }
    }

    public boolean tienePedidoAnterior() {
        return mementoPrevio != null;
    }

    public List<IItemMenu> obtenerItemsPedidoAnterior() {
        if (mementoPrevio != null) {
            return new ArrayList<>(mementoPrevio.items);
        }
        return new ArrayList<>();
    }

    @Override
    public IIterator<IItemMenu> crearIterador() {
        return new IteradorItems(items);
    }
}