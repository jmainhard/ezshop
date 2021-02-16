package com.mycompany.proyectoboletas;

import java.util.Date;

/**
 * @author Esteban
 */
public abstract class Comprobante implements Imprimible {
    private int numComprobante;
    private Date fecha;
    private double total;
    private Cliente cliente;

    public Comprobante(int numComprobante, Date fecha, double total) {
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.total = total;
    }

    public Comprobante(Cliente cliente) {
        this.cliente = cliente;
        // TODO
    }
    
    @Override
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
