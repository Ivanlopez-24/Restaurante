package modelo;

import java.util.ArrayList;
import java.util.List;

public class MenuItaliano extends MenuCompuesto {
    private List<IItemMenu> bebidas = new ArrayList<>();
    private List<IItemMenu> combos = new ArrayList<>();

    public MenuItaliano(String nombre) {
        super(nombre);
        // Crear platos italianos
        Plato pizza = new Plato("Pizza", 15.0);
        Plato pasta = new Plato("Pasta", 13.0);
        Plato lasagna = new Plato("Lasaña", 14.0);
        agregarItem(pizza);
        agregarItem(pasta);
        agregarItem(lasagna);

        // Crear bebidas
        Bebida cocacola = new Bebida("CocaCola", 5.0);
        Bebida postobom = new Bebida("Postobom", 4.5);
        Bebida limonada = new Bebida("Limonada", 4.0);
        Bebida jugoUva = new Bebida("Jugo de Uva", 5.5);
        bebidas.add(cocacola);
        bebidas.add(postobom);
        bebidas.add(limonada);
        bebidas.add(jugoUva);

        // Crear combos (plato + bebida)
        Combo combo1 = new Combo();
        combo1.agregarItem(pizza);
        combo1.agregarItem(cocacola);
        combos.add(combo1);

        Combo combo2 = new Combo();
        combo2.agregarItem(pasta);
        combo2.agregarItem(postobom);
        combos.add(combo2);
    }

    public IIterator<IItemMenu> crearIteradorBebidas() {
        return new IteradorMenu(bebidas);
    }

    public IIterator<IItemMenu> crearIteradorPlatos() {
        return crearIterador();
    }

    public List<IItemMenu> getCombos() {
        return combos;
    }
}
