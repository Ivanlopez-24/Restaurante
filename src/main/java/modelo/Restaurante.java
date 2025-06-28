/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ivanl
 */
public class Restaurante {
    private static Restaurante instancia;
    private Restaurante(){}
        public static Restaurante getInstancia() {
        if (instancia == null) {
            instancia = new Restaurante();
        }
        return instancia;
    }
}
