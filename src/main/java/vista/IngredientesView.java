package vista;

import modelo.FlyweightFactory;
import modelo.Ingrediente;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Vista para mostrar los ingredientes y su uso compartido (Flyweight).
 */
public class IngredientesView extends JFrame {
    private JTextArea areaIngredientes;
    private FlyweightFactory flyweightFactory;

    public IngredientesView(FlyweightFactory flyweightFactory) {
        this.flyweightFactory = flyweightFactory;
        setTitle("Ingredientes Compartidos (Flyweight)");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        areaIngredientes = new JTextArea();
        areaIngredientes.setEditable(false);
        areaIngredientes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaIngredientes);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Actualiza la vista mostrando los ingredientes y la cantidad de veces que se usan.
     * @param usoIngredientes Mapa con nombre de ingrediente y cantidad de usos.
     */
    public void actualizarIngredientes(Map<String, Integer> usoIngredientes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ingrediente - Veces Usado\n");
        sb.append("--------------------------\n");
        for (Map.Entry<String, Integer> entry : usoIngredientes.entrySet()) {
            sb.append(String.format("%-15s : %d\n", entry.getKey(), entry.getValue()));
        }
        areaIngredientes.setText(sb.toString());
    }
}
