package com.mycompany.proyectoboletas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge M.
 *         Maximiliano C.
 *         Esteban E.
 */

public class Canasta {
    private List<Producto> productos;
    
    public Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public Canasta(ArrayList<Producto> productos) {
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
    
    public boolean addProducto(Producto p) {
        try {
            // este if: en caso de que se utilizara polimorfismo,
            // de otra forma sería error de compilación (en lugar de IllegalArgument)
            if (p.getClass() != Producto.class) {
                throw new IllegalArgumentException();
            }
            if (!p.equals(null)) {
                if (productos.add(p)) {
                    return true;
                }
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.err.println(e + ": Producto nulo o no inicializado");
        } catch (IllegalArgumentException e) { // este catch: debiera sólo en caso de polimorfismo
            System.err.println(e + ": No es posible agregar este elemento");
        } catch (Exception e) {
            System.err.println(e + ": Error al intentar añadir producto");
        }
        
        return false;
    }
    
    // TODO
    public boolean removeProducto(Producto p) {
        productos.remove(p);
        return false;
    }
    
    // TODO
    public void updateIngresos() {
        throw new UnsupportedOperationException("updateIngresos no opera aún");
    }
    
    // getters y setters
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    
    
}
