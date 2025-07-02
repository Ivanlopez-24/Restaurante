package test;

import modelo.MenuSimple;
import modelo.Pedido;
import modelo.adaptador.AdaptadorPago;
import modelo.adaptador.IPago;
import modelo.chain.HandlerPedido;
import modelo.chain.ValidarPagoHandler;
import modelo.chain.ValidarStockHandler;
import modelo.fachada.FacadeRestaurante;
import modelo.mediator.MediatorRestaurante;
import modelo.template.ProcesoPreparacionItaliana;
import modelo.template.ProcesoPreparacionMexicana;

/**
 * Clase para probar varios patrones implementados.
 */
public class PruebaPatrones {
    public static void main(String[] args) {
        System.out.println("=== Pruebas de Template Method ===");
        ProcesoPreparacionItaliana procesoItaliana = new ProcesoPreparacionItaliana();
        procesoItaliana.preparar();

        ProcesoPreparacionMexicana procesoMexicana = new ProcesoPreparacionMexicana();
        procesoMexicana.preparar();

        System.out.println("\n=== Pruebas de Facade ===");
        FacadeRestaurante facade = new FacadeRestaurante();
        MenuSimple pizza = new MenuSimple("Pizza", 8.5);
        MenuSimple pasta = new MenuSimple("Pasta", 7.0);
        facade.agregarItemAlPedido(pizza);
        facade.agregarItemAlPedido(pasta);
        System.out.println("Total pedido: " + facade.calcularTotal());

        System.out.println("\n=== Pruebas de Adapter ===");
        IPago pago = new AdaptadorPago();
        pago.pagar(15.0);

        System.out.println("\n=== Pruebas de Chain of Responsibility ===");
        Pedido pedido = new Pedido();
        HandlerPedido validarStock = new ValidarStockHandler();
        HandlerPedido validarPago = new ValidarPagoHandler();
        validarStock.setSiguiente(validarPago);
        validarStock.manejar(pedido);

        System.out.println("\n=== Pruebas de Mediator ===");
        MediatorRestaurante mediator = new MediatorRestaurante(null, pedido);
        mediator.notificar("pedidoRealizado");
        mediator.notificar("pedidoListo");
    }
}
