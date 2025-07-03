package modelo;

import java.util.List;

public class IteradorMenu implements IIterator<IItemMenu> {
    private List<IItemMenu> items;
    private int posicion = 0;

    public IteradorMenu(List<IItemMenu> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return posicion < items.size();
    }

    @Override
    public IItemMenu next() {
        if (this.hasNext()) {
            return items.get(posicion++);
        }
        return null;
    }
}
