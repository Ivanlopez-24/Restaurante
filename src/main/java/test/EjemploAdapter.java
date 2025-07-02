package test;

import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;

/**
 * Ejemplo de uso del patrón Adapter con objetos.
 */
public class EjemploAdapter {
    public void realizarPago() {
        IPago pago = new AdaptadorPago();
        pago.pagar(25.0);
    }
}
