/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas;

/**
 *
 * @author campo
 */
public class Inventario implements Imprimible {

    @Override
    public void imprimir() {
        System.out.println("helo");
    }

    public Inventario() {
    }

    public Producto addProducto(Producto p) {
        return p;
    }

    public Producto delProducto(Producto p) {

        return p;
    }

    public void mostrarInventario() {
    }
}
