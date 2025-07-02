package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PagoView extends JFrame {
    private JLabel lblTotal;
    private JButton btnPagar;
    private JTextField txtMonto;

    public PagoView(double total) {
        setTitle("Realizar Pago");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTotal = new JLabel(String.format("Total a pagar: $%.2f", total));
        txtMonto = new JTextField(10);
        btnPagar = new JButton("Pagar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(lblTotal);
        panel.add(txtMonto);
        panel.add(btnPagar);

        getContentPane().add(panel);
    }

    public String getMontoIngresado() {
        return txtMonto.getText();
    }

    public void agregarListenerPagar(ActionListener listener) {
        btnPagar.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
