package com.mycompany.proyectoboletas.modelo;

import com.google.gson.annotations.SerializedName;

// Separar atributo stock en una clase aparte
// Stock: pdcto: Producto, cantidad: int
public class Producto {
    @SerializedName("ID Producto")
    private int id;
    @SerializedName("Nombre Producto")
    private String nombre;
    @SerializedName("Precio")
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre+ " / $" + precio+ " / "+ id;
    }
    
    
    
    
}
