// Iterador concreto para lista de items en Pedido
package modelo;

import java.util.List;

public class IteradorItems implements IIterator<IItemMenu> {
    private List<IItemMenu> items;
    private int posicion = 0;

    public IteradorItems(List<IItemMenu> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return posicion < items.size();
    }

    @Override
    public IItemMenu next() {
        return items.get(posicion++);
    }
}