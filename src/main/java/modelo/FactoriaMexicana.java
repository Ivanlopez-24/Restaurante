/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public class FactoriaMexicana implements IFactoriaPlatos {

    @Override
    public IItemMenu crearPlatoPrincipal() {
        // Ejemplo: tacos al pastor
        return (IItemMenu) new Plato("Tacos al Pastor", 30000);
    }

    @Override
    public IItemMenu crearBebida() {
        // Ejemplo: Jarrito de tamarindo
        return new Bebida("Jarrito de Tamarindo", 8000);
    }
}
