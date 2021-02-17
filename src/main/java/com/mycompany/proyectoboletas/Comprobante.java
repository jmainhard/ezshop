package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author Esteban
 */
public class Comprobante implements Imprimible {
    //Nombres Serializados para dar limpieza al JSON
    @SerializedName("Numero Comprobante")
    private int numComprobante;
    @SerializedName("Fecha")
    private Date fecha;
    @SerializedName("Total Compra")
    private double total;
    @SerializedName("Cliente")
    private Cliente cliente;

    public Comprobante(int numComprobante, Date fecha, double total) {
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.total = total;
    }

    public Comprobante(Cliente cliente) {
        this.cliente = cliente;
        this.fecha = new Date();
        // TODO
    }
    
    @Override
    public void imprimir(){
        System.out.println("Hola");
    }
    
    
    //Actualiza TOTAL
    public void calcTotal(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = this.cliente.getCanasta().getProductos();
        for (Producto p: productos) {
            this.total+=p.getPrecio();
        }
        System.out.println("Total calculado");
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
