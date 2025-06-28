/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;
import modelo.Pedido;
import modelo.IItemMenu;
/**
 *
 * @author ivanl
 */
public interface IControladorPedido {
    /**
     * Crea un nuevo pedido.
     * @return el pedido recién creado
     */
    Pedido crearPedido();

    /**
     * Agrega un ítem al pedido.
     * @param pedido el pedido al que agregar el ítem
     * @param item el ítem a agregar
     */
    void agregarItemPedido(Pedido pedido, IItemMenu item);

    /**
     * Finaliza y procesa el pedido.
     * @param pedido el pedido a finalizar
     */
    void finalizarPedido(Pedido pedido);
}
