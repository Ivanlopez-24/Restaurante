package test;

import modelo.ConfiguracionSingleton;

/**
 * Ejemplo de uso del patrón Singleton con objetos.
 */
public class EjemploSingleton {
    public void mostrarConfiguracion() {
        ConfiguracionSingleton config = ConfiguracionSingleton.getInstancia();
        System.out.println("Configuración del restaurante:");
        System.out.println("Nombre: " + config.getNombreRestaurante());
        System.out.println("Dirección: " + config.getDireccion());
    }
}
