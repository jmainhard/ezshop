package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * @author Jorge M., Maximiliano C., Esteban E.
 */

public class Canasta {
    //Nombres Serializados para dar limpieza al JSON
    @SerializedName("Productos")
    private ArrayList<Producto> productos;
    private double total; // Importante: se actualiza al invocar getTotal()
    
    public Canasta() {
        this.productos = new ArrayList<>();
    }
    
    public Canasta(ArrayList<Producto> productos) {
        this.productos = productos;
        this.total = calcTotal();
    }
    
    public boolean addProducto(Producto p) {
        try {
            // En caso de polimorfismo a futuro
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
    
    public boolean removeProducto(Producto p) {
        try {
            return productos.remove(p);
        } catch (NullPointerException e) {
            System.err.println(e + ": Producto nulo o no inicializado");
        } catch (Exception e) {
            System.err.println(e + ": Error al intentar eliminar producto");
        }
        
        return false;
    }
    
    // Actualiza total
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
    
    public double getTotal() {
        total = calcTotal();
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
