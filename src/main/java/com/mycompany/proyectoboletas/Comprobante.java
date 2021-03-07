package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Esteban
 */
public abstract class Comprobante implements Imprimible {
    //Nombres Serializados para dar limpieza al JSON
    @SerializedName("Numero Comprobante")
    private int numComprobante;
    @SerializedName("Fecha")
    private Date fecha;
    @SerializedName("Cliente")
    private Cliente cliente;
    @SerializedName("Total Compra")
    private double total;

    public Comprobante(){
    }

    public Comprobante(Cliente cliente) {
        this.cliente = cliente;
        this.fecha = new Date();
        // TODO
    }

    @Override
    public void imprimir() {
        System.out.println("");
    }
    
    //Actualiza TOTAL
    public void calcTotal(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = this.cliente.getCanasta().getProductos();
        for (Producto p: productos) {
            this.total += p.getPrecio();
        }
    }
    
    public int getNumComprobante() {
        return numComprobante;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public Cliente getCliente(){
        return cliente;
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
        String strBuilder = "\n              -- COMPROBANTE --\n";
        String strFecha = DateFormat.
            getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).
            format(this.getFecha());
        
        strBuilder += "---------------------------------------------\n\n";
        strBuilder += "Tipo: "+ this.getClass().getSimpleName()+ "\n";
        strBuilder += "Num comprobante: "+ this.getNumComprobante()+ "\n";
        strBuilder += "Fecha: "+ strFecha+ "\n";
        strBuilder += cliente.toString();
        strBuilder += cliente.getCanasta().toString();
        
        return strBuilder;
    }
    
    
}
