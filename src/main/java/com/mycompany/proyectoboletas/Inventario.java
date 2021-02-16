package com.mycompany.proyectoboletas;

/**
 * @author Maximiliano C., Esteban E., Jorge M.
 */
public class Inventario implements Imprimible {

    @Override
    public void imprimir() {
        System.out.println("helo");
    }

    public Inventario() {
    }

    // TODO
    public Producto addProducto(Producto p) {
        return p;
    }
    
    public Producto delProducto(Producto p) {
        // TODO
        return p;
    }

    public void mostrarInventario() {
    }
}
