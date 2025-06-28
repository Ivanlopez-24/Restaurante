//- Controlador para manejar eventos de PedidoView y cargar menú desde archivo
package controlador;

import modelo.IItemMenu;
import modelo.Pedido;
import modelo.ProxyAccesoDatos;
import vista.PedidoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private Pedido modelo;
    private PedidoView vista;
    private List<IItemMenu> menuItems;

    public PedidoController(Pedido modelo, PedidoView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.menuItems = cargarMenuDesdeArchivo("data/menu.txt");

        // Actualizar combo con menú cargado
        vista.comboPlatos.setModel(new javax.swing.DefaultComboBoxModel<>(menuItems.toArray(new IItemMenu[0])));

        // Listener para botón agregar
        this.vista.agregarListenerAgregar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IItemMenu plato = vista.getPlatoSeleccionado();
                if (plato != null) {
                    modelo.agregarItem(plato);
                    actualizarVista();
                }
            }
        });

        actualizarVista();
    }

    private void actualizarVista() {
        StringBuilder sb = new StringBuilder();
        for (IItemMenu item : modelo.getItems()) {
            sb.append(item.getNombre()).append(" - $").append(item.getPrecio()).append("\n");
        }
        vista.actualizarPedido(sb.toString(), modelo.getPrecioTotal());
    }

    private List<IItemMenu> cargarMenuDesdeArchivo(String rutaArchivo) {
        List<IItemMenu> menu = new ArrayList<>();
        ProxyAccesoDatos proxy = new ProxyAccesoDatos();
        proxy.abrirArchivo(rutaArchivo);
        String datos = proxy.leerDatos();
        proxy.cerrarArchivo();

        if (datos != null && !datos.isEmpty()) {
            String[] lineas = datos.split("\n");
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String id = partes[0].trim();
                    String nombre = partes[1].trim();
                    double precio = Double.parseDouble(partes[2].trim());
                    // Aquí se puede usar una fábrica para crear objetos más complejos
                    menu.add(new modelo.Plato(nombre, precio));
                }
            }
        }
        return menu;
    }
}