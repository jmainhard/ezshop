package com.mycompany.proyectoboletas;

import java.util.Date;

/**
 * @author Esteban
 */
public class Boleta extends Comprobante {

    public Boleta(int numComprobante, Date fecha, double total) {
        super(numComprobante, fecha, total);
    }

    public Boleta(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String toString() {
        return "Boleta{" + '}';
    }
    
}
