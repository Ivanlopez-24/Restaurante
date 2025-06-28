// AccesoDatosReal.java - Implementación real que usa archivo txt para almacenamiento
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccesoDatosReal implements IAccesoDatos {
    private BufferedReader lector;
    private BufferedWriter escritor;
    private String rutaArchivo;

    @Override
    public void abrirArchivo(String ruta) {
        this.rutaArchivo = ruta;
        try {
            lector = new BufferedReader(new FileReader(rutaArchivo));
            escritor = new BufferedWriter(new FileWriter(rutaArchivo, true)); // modo append
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            if (lector != null) lector.close();
            if (escritor != null) escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String leerDatos() {
        StringBuilder contenido = new StringBuilder();
        try {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }

    @Override
    public void escribirDatos(String datos) {
        try {
            escritor.write(datos);
            escritor.newLine();
            escritor.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}