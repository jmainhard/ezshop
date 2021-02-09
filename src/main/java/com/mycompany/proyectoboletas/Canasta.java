package com.mycompany.proyectoboletas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge M.
 *         Maximiliano C.
 *         Esteban E.
 */

public class Canasta {
    List<Producto> productos;
    
    public void Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public void Canasta(List<Producto> productos) {
        this.productos = productos;
    }
    
    // TODO
    public void compra() {
        throw new UnsupportedOperationException("Compra no soportada");
    }
    
    public void addProducto(Producto p) {
        productos.add(p);
    }
    
    public void removeProducto(Producto p) {
        productos.remove(p);
    }
    
    // TODO
    public void updateIngresos() {
        throw new UnsupportedOperationException("updateIngresos no soportado");
    }
    
    // getters y setters
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
    
    
}
