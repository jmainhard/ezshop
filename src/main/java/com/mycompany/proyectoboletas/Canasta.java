package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge M.
 *         Maximiliano C.
 *         Esteban E.
 */

public class Canasta {
    //Nombres Serializados para dar limpieza al JSON
    @SerializedName("Productos")
    private ArrayList<Producto> productos;
    
    public Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public Canasta(ArrayList<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    // TODO
    // Cambiaré el método compra a cliente para que allí se haga limpieza del cliente para una nueva compra
    // debido a que canasta no tiene a cliente entre sus atributos y-
    // es allá donde se crea una nueva canasta por cada cliente
//    public void compra() {
//        throw new UnsupportedOperationException("Compra no soportada");
//    }
    
    public boolean addProducto(Producto p) {
        try {
            // este if: en caso de que se utilizara polimorfismo,
            // de otra forma sería error de compilación (en lugar de IllegalArgument)
            if (p.getClass() != Producto.class) {
                throw new IllegalArgumentException();
            }
            if (!p.equals(null)) {
                return productos.add(p);
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
    
    // TODO añadir exceptions
    public boolean removeProducto(Producto p) {
        productos.remove(p);
        return false;
    }
    
    // TODO
    public void updateIngresos() {
        throw new UnsupportedOperationException("updateIngresos no opera aún");
    }
    
    // getters y setters
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    
    
}
