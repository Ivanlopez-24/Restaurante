/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
@FunctionalInterface
public interface IDescuentoStrategy {
        /**
     * Calcula el monto de descuento a aplicar.
     * @param pedido Pedido sobre el cual calcular el descuento
     * @return valor del descuento
     */
    double calcularDescuento(Pedido pedido);
}
