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
    private JButton btnTerminarPedido;
    private JTextArea areaPedido;
    private JLabel lblTotal;
    private JTextArea areaMensajes;
    private JLabel lblBienvenido;

    public PedidoView(List<IItemMenu> menuItems) {
        setTitle("Gestión de Pedidos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);

        comboPlatos = new JComboBox<>(menuItems.toArray(new IItemMenu[0]));
        btnAgregar = new JButton("Agregar al Pedido");
        btnTerminarPedido = new JButton("Terminar Pedido");
        areaPedido = new JTextArea(15, 40);
        areaPedido.setEditable(false);
        areaPedido.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 102, 51));

        areaMensajes = new JTextArea(8, 40);
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes del sistema"));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.add(new JLabel("Seleccione un plato:"));
        panel.add(comboPlatos);
        panel.add(btnAgregar);
        panel.add(btnTerminarPedido);

        JPanel panelPedido = new JPanel();
        panelPedido.setLayout(new BorderLayout(10, 10));
        panelPedido.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido"));
        panelPedido.add(new JScrollPane(areaPedido), BorderLayout.CENTER);
        panelPedido.add(lblTotal, BorderLayout.SOUTH);

        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BorderLayout());
        panelMensajes.add(new JScrollPane(areaMensajes), BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(lblBienvenido, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panelPedido, BorderLayout.SOUTH);
        getContentPane().add(panelMensajes, BorderLayout.EAST);
    }

    public IItemMenu getPlatoSeleccionado() {
        return (IItemMenu) comboPlatos.getSelectedItem();
    }

    public void agregarListenerAgregar(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void agregarListenerTerminarPedido(ActionListener listener) {
        btnTerminarPedido.addActionListener(listener);
    }

    public void actualizarPedido(String textoPedido, double total) {
        areaPedido.setText(textoPedido);
        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    public void actualizarMensajes(String mensajes) {
        areaMensajes.setText(mensajes);
    }
}
