package modelo;

import java.util.List;

public interface IAgregado<T> {
    IIterator<T> crearIterador();
}