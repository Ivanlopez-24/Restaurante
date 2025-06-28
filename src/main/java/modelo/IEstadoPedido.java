/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public interface IEstadoPedido {
    /**
     * Opera sobre el pedido según su estado.
     * @param pedido el pedido que cambia de estado
     */
    void manejar(Pedido pedido);
}
