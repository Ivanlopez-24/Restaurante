package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import modelo.Producto;
import modelo.controlador.MenuController;
import modelo.factory.PlatoFactory;

public class MainFrame extends JFrame {
    private MenuController menuController;
    private JTextArea displayArea;

    public MainFrame() {
        super("Restaurante MVC");
        this.menuController = new MenuController(new PlatoFactory());

        setLayout(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar Plato");
        addButton.addActionListener(e -> agregarPlato());
        add(addButton, BorderLayout.SOUTH);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void agregarPlato() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del plato:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String precioStr = JOptionPane.showInputDialog(this, "Precio del plato:");
        if (precioStr == null || precioStr.trim().isEmpty()) return;

        try {
            double precio = Double.parseDouble(precioStr);
            Producto plato = menuController.crearProducto(nombre, precio);
            displayArea.append(plato.descripcion() + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
