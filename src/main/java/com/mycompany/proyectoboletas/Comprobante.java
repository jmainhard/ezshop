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
public class Comprobante {
    private int numComprobante;
    private Date fecha;
    private double total;

    public Comprobante(int numComprobante, Date fecha, double total) {
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.total = total;
    }

    public Comprobante(Cliente cliente) {
        
    }
    
    public void imprimir(){
    
    }
    
    public double calcTotal(){
        
        return 0;
    }
    
    public int getNumComprobante() {
        return numComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setNumComprobante(int numComprobante) {
        this.numComprobante = numComprobante;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Comprobante{" + "numComprobante=" + numComprobante + ", fecha=" + fecha + ", total=" + total + '}';
    }
    
    
}
