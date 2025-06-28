/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public interface IFactoriaPlatos {
        /**
     * Crea el plato principal de la factoría.
     * @return un IItemMenu que representa el plato principal.
     */
    IItemMenu crearPlatoPrincipal();

    /**
     * Crea la bebida asociada de la factoría.
     * @return un IItemMenu que representa la bebida.
     */
    IItemMenu crearBebida();
}
