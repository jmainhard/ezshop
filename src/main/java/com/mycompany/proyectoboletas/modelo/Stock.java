package com.mycompany.proyectoboletas.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 */
public class Stock extends Producto {
    @SerializedName("Cantidad")
    private int cantidad;

    public Stock(int id, String nombre, double precio, int cantidad) {
        super(id, nombre, precio);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        String strBuilder;
        
        String pdctoNom = super.getNombre()+ "\n";
        String pdctoId = super.getId()+ "\n";
        String pdctoCant = String.valueOf(this.cantidad)+ "\n";
        String pdctoPre = "$" + String.valueOf((int) super.getPrecio()+ "\n");
        
        strBuilder = String.format("Nombre: %20s", pdctoNom);
        strBuilder += String.format("ID: %24s", pdctoId);
        strBuilder += String.format("Cantidad disp.: %12s", pdctoCant);
        strBuilder += String.format("Precio: %20s", pdctoPre);
        
        return strBuilder;
    }
}
