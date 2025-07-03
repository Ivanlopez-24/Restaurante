package modelo;

import java.util.ArrayList;
import java.util.List;

public class MenuMexicano extends MenuSimple {
    private List<IItemMenu> platos = new ArrayList<>();
    private List<IItemMenu> bebidas = new ArrayList<>();
    private List<IItemMenu> combos = new ArrayList<>();

    public MenuMexicano(String nombre, double precio, FlyweightFactory flyweightFactory) {
        super(nombre, precio);
        // Crear platos mexicanos
        Plato taco = new Plato("Taco", 10.0);
        Plato mole = new Plato("Mole", 12.0);
        Plato pozole = new Plato("Pozole", 11.0);
        Plato chile = new Plato("Chile", 9.0);
        platos.add(taco);
        platos.add(mole);
        platos.add(pozole);
        platos.add(chile);

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
        combo1.agregarItem(taco);
        combo1.agregarItem(cocacola);
        combos.add(combo1);

        Combo combo2 = new Combo();
        combo2.agregarItem(mole);
        combo2.agregarItem(postobom);
        combos.add(combo2);
    }

    public IIterator<IItemMenu> crearIteradorPlatos() {
        return new IteradorMenu(platos);
    }

    public IIterator<IItemMenu> crearIteradorBebidas() {
        return new IteradorMenu(bebidas);
    }

    public List<IItemMenu> getCombos() {
        return combos;
    }
}
