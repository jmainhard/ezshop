package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 */
public class Stock extends Producto {
    @SerializedName("Cantidad")
    private int cantidad;

    public Stock(int cantidad, int id, String nombre, double precio) {
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
        String pdctoPre = "$" + String.valueOf((int) super.getPrecio()+ "\n");
        String pdctoCant = String.valueOf(this.cantidad)+ "\n";
        
        strBuilder = String.format("Nombre: %20s", pdctoNom);
        strBuilder += String.format("ID: %24s", pdctoId);
        strBuilder += String.format("Precio: %20s", pdctoPre);
        strBuilder += String.format("Cantidad disponible: %7s", pdctoCant);
        
        return strBuilder;
    }
}
