//- Vista para agregar y mostrar pedidos con carga desde archivo
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import modelo.IItemMenu;

public class PedidoView extends JFrame {
    public JComboBox<IItemMenu> comboPlatos;
    private JComboBox<IItemMenu> comboBebidas;
    private JButton btnAgregar;
    private JButton btnAgregarBebida;
    private JButton btnTerminarPedido;
    private JButton btnMostrarIngredientes;  // Nuevo botón para mostrar ingredientes
    private JTextArea areaPedido;
    private JTextArea areaPedidoAnterior;
    private JLabel lblTotal;
    private JTextArea areaMensajes;
    private JLabel lblBienvenido;

    public PedidoView(List<IItemMenu> menuItems, List<IItemMenu> bebidas) {
        setTitle("Gestión de Pedidos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);

        comboPlatos = new JComboBox<>(menuItems.toArray(new IItemMenu[0]));
        comboBebidas = new JComboBox<>(bebidas.toArray(new IItemMenu[0]));
        btnAgregar = new JButton("Agregar al Pedido");
        btnAgregarBebida = new JButton("Agregar Bebida");
        btnTerminarPedido = new JButton("Terminar Pedido");
        btnMostrarIngredientes = new JButton("Mostrar Ingredientes");  // Nuevo botón para mostrar ingredientes
        areaPedido = new JTextArea(15, 40);
        areaPedido.setEditable(false);
        areaPedido.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 102, 51));

        areaPedidoAnterior = new JTextArea(10, 40);
        areaPedidoAnterior.setEditable(false);
        areaPedidoAnterior.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaPedidoAnterior.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido Anterior"));

        areaMensajes = new JTextArea(8, 40);
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes del sistema"));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.add(new JLabel("Seleccione un plato:"));
        panel.add(comboPlatos);
        panel.add(btnAgregar);
        panel.add(new JLabel("Seleccione una bebida:"));
        panel.add(comboBebidas);
        panel.add(btnAgregarBebida);
        panel.add(btnTerminarPedido);
        panel.add(btnMostrarIngredientes);  // Añadir botón al panel

        JPanel panelPedido = new JPanel();
        panelPedido.setLayout(new BorderLayout(10, 10));
        panelPedido.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido"));
        panelPedido.add(new JScrollPane(areaPedido), BorderLayout.CENTER);
        panelPedido.add(lblTotal, BorderLayout.SOUTH);

        JPanel panelPedidoAnterior = new JPanel();
        panelPedidoAnterior.setLayout(new BorderLayout(10, 10));
        panelPedidoAnterior.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido Anterior"));
        panelPedidoAnterior.add(new JScrollPane(areaPedidoAnterior), BorderLayout.CENTER);

        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BorderLayout());
        panelMensajes.add(new JScrollPane(areaMensajes), BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(lblBienvenido, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panelPedido, BorderLayout.SOUTH);
        getContentPane().add(panelPedidoAnterior, BorderLayout.EAST);
        getContentPane().add(panelMensajes, BorderLayout.WEST);
    }


    public IItemMenu getPlatoSeleccionado() {
        return (IItemMenu) comboPlatos.getSelectedItem();
    }

    public IItemMenu getBebidaSeleccionada() {
        return (IItemMenu) comboBebidas.getSelectedItem();
    }

    public void agregarListenerAgregar(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void agregarListenerAgregarBebida(ActionListener listener) {
        btnAgregarBebida.addActionListener(listener);
    }

    public void agregarListenerTerminarPedido(ActionListener listener) {
        btnTerminarPedido.addActionListener(listener);
    }

    public void agregarListenerMostrarIngredientes(ActionListener listener) {
        btnMostrarIngredientes.addActionListener(listener);
    }

    public void agregarListenerReporteVisitor(ActionListener listener) {
        // Crear un nuevo botón para el reporte Visitor
        JButton btnReporteVisitor = new JButton("Reporte Visitor");
        btnReporteVisitor.addActionListener(listener);

        // Añadir el botón al panel principal (suponiendo que el primer panel es el de botones)
        // Aquí se asume que el layout es FlowLayout y el primer panel es accesible
        // Si no, se puede crear un panel específico para botones y añadirlo allí
        getContentPane().add(btnReporteVisitor, BorderLayout.NORTH);

        // Forzar actualización de la interfaz para mostrar el nuevo botón
        revalidate();
        repaint();
    }

    public void actualizarPedido(String textoPedido, double total) {
        areaPedido.setText(textoPedido);
        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    public void limpiarDetallePedido() {
        areaPedido.setText("");
        lblTotal.setText("Total: $0.00");
    }

    public void actualizarPedidoAnterior(String textoPedidoAnterior) {
        areaPedidoAnterior.setText(textoPedidoAnterior);
    }

    public void actualizarMensajes(String mensajes) {
        areaMensajes.setText(mensajes);
    }
}
