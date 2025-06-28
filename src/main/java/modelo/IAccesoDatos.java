// IAccesoDatos.java - Interfaz para acceso a datos mediante archivo txt
package modelo;

public interface IAccesoDatos {
    void abrirArchivo(String ruta);
    void cerrarArchivo();
    String leerDatos();
    void escribirDatos(String datos);
}