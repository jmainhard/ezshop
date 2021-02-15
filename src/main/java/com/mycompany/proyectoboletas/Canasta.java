package com.mycompany.proyectoboletas;

import java.util.ArrayList;
// import java.util.List;

/**
 * @author Jorge M.
 *         Maximiliano C.
 *         Esteban E.
 */

public class Canasta {
    private ArrayList<Producto> productos;
    
    public void Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public void Canasta(ArrayList<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }
    
    // TODO
    // Disminuye el stock de (uno o más) productos vendidos
    // ? Genera comprobante -> ? entrega detalle (String) de los productos en canasta
    // X Limpia esta canasta para una nueva venta -> no hace falta limpiar -
    // la canasta debido a que cada cliente tiene una canasta con referencia unica en tiempo de ejecución
    public void compra() {
        throw new UnsupportedOperationException("Compra no soportada");
    }
    
    public void addProducto(Producto p) {
//        try {
//            if (productos.add(p)) {
//                return true;
//            }
//        } catch (Exception e) {
//        }
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
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    
    
    
}
