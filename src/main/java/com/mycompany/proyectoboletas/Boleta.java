package com.mycompany.proyectoboletas;

import java.util.Date;

/**
 * @author Esteban
 */
public class Boleta extends Comprobante {

    public Boleta(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void mostrar() {
    }

    @Override
    public void imprimir(){
        System.out.println("Hola");
    }
    @Override
    public String toString() {
        return "Boleta{" + '}';
    }
    
}
