package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;


public class Producto {
    @SerializedName("ID Producto")
    private int id;
    @SerializedName("Nombre Producto")
    private String nombre;
    @SerializedName("Stock")
    private int stock;
    @SerializedName("Precio")
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + '}';
    }
    
    
    
    
}
