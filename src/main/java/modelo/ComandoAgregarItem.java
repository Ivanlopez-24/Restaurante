package modelo;

public class ComandoAgregarItem implements ICommand {
    private Pedido pedido;
    private IItemMenu item;

    public ComandoAgregarItem(Pedido pedido, IItemMenu item) {
        this.pedido = pedido;
        this.item = item;
    }

    @Override
    public void ejecutar() {
        pedido.agregarItem(item);
    }

    @Override
    public void deshacer() {
        pedido.getItems().remove(item);
    }
}