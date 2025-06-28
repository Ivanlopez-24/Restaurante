// ProxyAccesoDatos.java - Proxy para controlar acceso al archivo
package modelo;

public class ProxyAccesoDatos implements IAccesoDatos {
    private AccesoDatosReal accesoReal;
    private boolean archivoAbierto = false;

    @Override
    public void abrirArchivo(String ruta) {
        if (!archivoAbierto) {
            accesoReal = new AccesoDatosReal();
            accesoReal.abrirArchivo(ruta);
            archivoAbierto = true;
        }
    }

    @Override
    public void cerrarArchivo() {
        if (archivoAbierto) {
            accesoReal.cerrarArchivo();
            archivoAbierto = false;
        }
    }

    @Override
    public String leerDatos() {
        if (archivoAbierto) {
            return accesoReal.leerDatos();
        } else {
            return "Archivo no abierto";
        }
    }

    @Override
    public void escribirDatos(String datos) {
        if (archivoAbierto) {
            accesoReal.escribirDatos(datos);
        }
    }
}