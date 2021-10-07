package com.mycompany.proyectoboletas.modelo;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * @author Jorge M., Maximiliano C., Esteban E.
 */

public class Canasta {
    // Nombres Serializados para dar limpieza al JSON
    @SerializedName("Productos")
    private ArrayList<Producto> productos;
        
    public Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public Canasta(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public boolean addProducto(Producto p) {
        try {
            // En caso de polimorfismo a futuro
            if (p.getClass() != Producto.class) {
                throw new IllegalArgumentException();
            }
            if (p == null) {
                throw new NullPointerException();
            } else {
                return productos.add(p);
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
    
    public boolean removeProducto(int id) {
        try {
            for (Producto producto : productos) {
                if (producto.getId() == id) {
                    return productos.remove(producto);
                }
            }
        } catch (NullPointerException e) {
            System.err.println(e + ": Producto nulo o no inicializado");
        } catch (Exception e) {
            System.err.println(e + ": Error al intentar eliminar producto");
        }
        return false;
    }
    
    /**
     * Total forma parte de comprobante, se calcula en {@code class} Comprobante
     * @see Comprobante
     */
    @Deprecated
    public double calcTotal() {
        double total = this.productos.stream()
                .mapToDouble(x -> x.getPrecio())
                .sum();
        return total;
    }

    // getters y setters
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    @Override
    public String toString() {
        String strBuilder = "Productos: (Nombre / Precio / ID)\n";
        strBuilder = productos.stream().
                map(p -> "-> "+ p.toString() + "\n").
                reduce(strBuilder, String::concat);
        
        return strBuilder;
    }
    
}
