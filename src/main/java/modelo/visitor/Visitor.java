package modelo.visitor;

import modelo.MenuCompuesto;
import modelo.MenuSimple;

/**
 * Interfaz Visitor para operaciones sobre elementos del menú.
 */
public interface Visitor {
    void visitarMenuSimple(MenuSimple menuSimple);
    void visitarMenuCompuesto(MenuCompuesto menuCompuesto);
}
