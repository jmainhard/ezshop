package com.mycompany.proyectoboletas;

import java.util.Date;

/**
 * @author Esteban
 */
public class Factura extends Comprobante{

    public Factura(int numComprobante, Date fecha, double total) {
        super(numComprobante, fecha, total);
    }

    public Factura(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String toString() {
        return "Factura{" + '}';
    }
    
    
}
