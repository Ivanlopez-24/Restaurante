package modelo;

/**
 * Clase Singleton para gestionar la configuración global del restaurante.
 */
public class ConfiguracionSingleton {
    private static ConfiguracionSingleton instancia;
    private String nombreRestaurante;
    private String direccion;
    private String telefono;

    // Constructor privado para evitar instanciación externa
    private ConfiguracionSingleton() {
        // Valores por defecto
        this.nombreRestaurante = "Restaurante Ejemplo";
        this.direccion = "Calle Falsa 123";
        this.telefono = "555-1234";
    }

    // Método público para obtener la instancia única
    public static ConfiguracionSingleton getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionSingleton();
        }
        return instancia;
    }

    // Getters y setters
    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
