package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 */
public class Stock extends Producto {
//    @SerializedName("Producto")
//    private Producto producto;

    @SerializedName("Stock")
    private int cantidad;

    public Stock(int cantidad, int id, String nombre, double precio) {
        super(id, nombre, precio);
        this.cantidad = cantidad;
    }

    // Getters y Setters
//    public Producto getProducto() {
//        return producto;
//    }
//
//    public void setProducto(Producto producto) {
//        this.producto = producto;
//    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

//    @Override
//    public String toString() {
//        String strBuilder;
//        
//        String pdctoNom = producto.getNombre()+ "\n";
//        String pdctoPre = String.valueOf(producto.getPrecio()+ "\n");
//        String pdctoCant = String.valueOf(cantidad)+ "\n";
//        
//        strBuilder = String.format("Producto: %17s", pdctoNom);
//        strBuilder += String.format("Precio: %19s", pdctoPre);
//        strBuilder += String.format("Stock: %20s", pdctoCant);
//        
//        return strBuilder;
//    }
}
