package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.utilidades.StockInsuficienteException;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.modelo.Imprimible;
import com.mycompany.proyectoboletas.modelo.Producto;
import com.mycompany.proyectoboletas.modelo.Stock;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Maximiliano C., Esteban E., Jorge M.
 */
public class InventarioController implements Imprimible {
    private JsonController<Stock> inventarioHandler;
    private ArrayList<Stock> inventario; 

    public InventarioController() {
        inventarioHandler = new JsonController<>("inventario.json",
        new TypeToken<Collection<Stock>>(){});
        inventario = inventarioHandler.cargarObjetos();
    }
    
    @Override
    public String toString() {
        String strBuilder;
//        cargar(); // sólo si se desea mostrar por pantalla el objeto y fué modificado en runtime
        strBuilder = "\n========== Inventario de productos ==========\n";
        strBuilder = inventario.stream().map(p -> p.toString()+ "\n").reduce(strBuilder, String::concat);
        return strBuilder;
    }
    
    @Override
    public void imprimir() {
        System.out.println(this.toString());
    }
    
    public boolean existeProducto(int id) {
        try {
            return inventario.stream().anyMatch(producto -> id == producto.getId());
        } catch (NullPointerException e) {
            System.err.println("Error al consultar producto "+ e);
        }
        return false;
    }
    
    public Producto getProducto(int id) throws StockInsuficienteException {
        for (Stock stockObj : inventario) {
            if (stockObj.getId() == id) {
                if (stockObj.getCantidad() == 0) {
                    throw new StockInsuficienteException("NO HAY STOCK!!");
                } else if (stockObj.getId() == id) {
                    return new Producto(
                            stockObj.getId(),
                            stockObj.getNombre(),
                            stockObj.getPrecio());
                }
            }
        }
        return null;
    }

    public boolean aumentarStock(int id) {
        for (Stock stock : inventario) {
            if (stock.getId() == id) {
                stock.setCantidad(stock.getCantidad() + 1);
                return true;
            }
        }
        return false;
    }

  
    public boolean reducirStock(int id) throws StockInsuficienteException {
        for (Stock stock : inventario) {
            if (stock.getId() == id) {
                if (stock.getCantidad() == 0) {
                    throw new StockInsuficienteException("NO HAY STOCK!!");
                } else {
                    int cantidad = stock.getCantidad();
                    stock.setCantidad(cantidad - 1);
                    return true;
                }
            }
        }
        return false;
    }

    ;
    public void nuevoProducto(String nombre, double precio, int cantidad) {
        int id = inventario.size() + 1;
        Stock stockProduct = new Stock(id, nombre, precio, cantidad);
        
        if (addProducto(stockProduct)) {
            System.out.println("\n-- Producto agregado al Inventario --");
            guardar();
        }

    }
    
    public boolean addProducto(Stock stockProduct) {
        return inventario.add(stockProduct);
    }

    public ArrayList<Stock> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Stock> inventario) {
        this.inventario = inventario;
    }
    
    /**
     * Guarda los objetos en inventario
     * @return {@code true} si fueron guardado con exito
     */
    public boolean guardar() {
        return inventarioHandler.guardarObjetos(inventario);
    }
    
    public void cargar() {
        inventario = inventarioHandler.cargarObjetos();
    }

}
