/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public class FactoriaItaliana implements IFactoriaPlatos {

    @Override
    public IItemMenu crearPlatoPrincipal() {
        // Ejemplo: pasta a la Bolognesa
        return new Plato("Pasta Bolognesa", 35000);
    }

    @Override
    public IItemMenu crearBebida() {
        // Ejemplo: copa de vino tinto
        return new Bebida("Vino Tinto (copa)", 12000);
    }
}