/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas;

import java.util.Date;

/**
 *
 * @author Esteban
 */
public class Boleta extends Comprobante{

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
