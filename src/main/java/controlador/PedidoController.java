package controlador;

import modelo.IItemMenu;
import modelo.fachada.FacadeRestaurante;
import vista.PedidoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PedidoController {
    private FacadeRestaurante facade;
    private PedidoView view;
    private List<IItemMenu> menuItems;

    public PedidoView getView() {
        return view;
    }

    public PedidoController(FacadeRestaurante facade, PedidoView view, List<IItemMenu> menuItems) {
        this.facade = facade;
        this.view = view;
        this.menuItems = menuItems;

        // Inicializar vista con menú pasado desde MainApp
        this.view.comboPlatos.setModel(new javax.swing.DefaultComboBoxModel<>(menuItems.toArray(new IItemMenu[0])));

        // Agregar listener para botón agregar
        this.view.agregarListenerAgregar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarItemAlPedido();
            }
        });

        actualizarVista();
    }

    private void agregarItemAlPedido() {
        IItemMenu item = view.getPlatoSeleccionado();
        if (item != null) {
            if (item instanceof modelo.MenuSimple) {
                facade.agregarItemAlPedido((modelo.MenuSimple) item);
            } else if (item instanceof modelo.MenuCompuesto) {
                facade.agregarMenuCompuestoAlPedido((modelo.MenuCompuesto) item);
            }
            actualizarVista();
            view.actualizarMensajes(""); // Limpiar mensajes al agregar ítem
        }
    }

    private void actualizarVista() {
        List<?> itemsPedido = facade.obtenerItemsPedido();
        StringBuilder textoPedido = new StringBuilder();
        double total = 0.0;
        for (Object obj : itemsPedido) {
            if (obj instanceof IItemMenu) {
                IItemMenu item = (IItemMenu) obj;
                textoPedido.append(item.getNombre()).append(" - $").append(item.getPrecio()).append("\n");
                total += item.getPrecio();
            }
        }
        view.actualizarPedido(textoPedido.toString(), total);
    }
}
