//- Vista para agregar y mostrar pedidos con carga desde archivo
package vista;

import modelo.IItemMenu;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PedidoView extends JFrame {
    public JComboBox<IItemMenu> comboPlatos;
    private JButton btnAgregar;
    private JTextArea areaPedido;
    private JLabel lblTotal;

    public PedidoView(List<IItemMenu> menuItems) {
        setTitle("Gestión de Pedidos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        comboPlatos = new JComboBox<>(menuItems.toArray(new IItemMenu[0]));
        btnAgregar = new JButton("Agregar al Pedido");
        areaPedido = new JTextArea(10, 30);
        areaPedido.setEditable(false);
        lblTotal = new JLabel("Total: $0.00");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(comboPlatos);
        panel.add(btnAgregar);

        JPanel panelPedido = new JPanel();
        panelPedido.setLayout(new BorderLayout());
        panelPedido.add(new JScrollPane(areaPedido), BorderLayout.CENTER);
        panelPedido.add(lblTotal, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(panelPedido, BorderLayout.CENTER);
    }

    public IItemMenu getPlatoSeleccionado() {
        return (IItemMenu) comboPlatos.getSelectedItem();
    }

    public void agregarListenerAgregar(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void actualizarPedido(String textoPedido, double total) {
        areaPedido.setText(textoPedido);
        lblTotal.setText(String.format("Total: $%.2f", total));
    }
}