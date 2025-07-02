package modelo.adaptador;

// Interfaz del sistema de pago externo simulado
interface SistemaPagoExterno {
    void realizarPagoExterno(double monto);
}

// Clase simulada del sistema de pago externo
class PagoExterno implements SistemaPagoExterno {
    @Override
    public void realizarPagoExterno(double monto) {
        System.out.println("Pago externo realizado por monto: " + monto);
    }
}

// Adaptador que convierte la interfaz del sistema externo a la interfaz del restaurante
public class AdaptadorPago implements IPago {
    private SistemaPagoExterno sistemaPagoExterno;

    public AdaptadorPago() {
        this.sistemaPagoExterno = new PagoExterno();
    }

    @Override
    public void pagar(double monto) {
        sistemaPagoExterno.realizarPagoExterno(monto);
    }
}
