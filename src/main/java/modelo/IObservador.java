/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public interface IObservador {
    /**
     * Actualiza al observador cuando cambia el pedido.
     * @param pedido El pedido que ha cambiado.
     */
    void actualizar(Pedido pedido);
}