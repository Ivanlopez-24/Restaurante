// MainApp.java - Clase principal para integrar Modelo, Vista y Controlador


import modelo.Pedido;
import vista.PedidoView;
import controlador.PedidoController;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Pedido modelo = new Pedido();
            PedidoView vista = new PedidoView(new java.util.ArrayList<>()); // Inicialmente vacío, controlador cargará menú
            PedidoController controlador = new PedidoController(modelo, vista);
            vista.setVisible(true);
        });
    }
}
