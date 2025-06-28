/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ivanl
 */

public class Notificador {
    private List<IObservador> observadores = new ArrayList<>();

    public void agregarObservador(IObservador obs) {
        observadores.add(obs);
    }

    public void notificar(Pedido pedido) {
        for (IObservador obs : observadores) {
            obs.actualizar(pedido);
        }
    }
}
