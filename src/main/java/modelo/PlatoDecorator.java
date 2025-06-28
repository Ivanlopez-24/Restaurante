package modelo;

public abstract class PlatoDecorator extends Producto {
    protected Producto platoDecorado;

    public PlatoDecorator(Producto platoDecorado) {
        super(platoDecorado.getNombre(), platoDecorado.getPrecio());
        this.platoDecorado = platoDecorado;
    }

    @Override
    public abstract String descripcion();
}