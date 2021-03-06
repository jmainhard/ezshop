package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.StockInsuficienteException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.Imprimible;
import com.mycompany.proyectoboletas.Producto;
import com.mycompany.proyectoboletas.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Maximiliano C., Esteban E., Jorge M.
 */
public class InventarioController implements Imprimible {
    private static ListController<Stock> inventarioController;
    private ArrayList<Stock> inventario; 

    public InventarioController() {
        inventarioController = new ListController<>("inventario.json",
        new TypeToken<Collection<Stock>>(){});
        inventario = inventarioController.cargarObjetos();
    }
    
    @Override
    public String toString() {
        String strBuilder;
//        cargar(); // sólo si se desea mostrar por pantalla el objeto y fué modificado en runtime
        
        strBuilder = "\n========== Inventario de productos ==========\n";
        strBuilder = inventario.stream().
                map(p -> p.toString()+ "\n").
                reduce(strBuilder, String::concat);
        
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
            if (stockObj.getCantidad() == 0) {
                throw new StockInsuficienteException("NO HAY STOCK!!");
            } else if (stockObj.getId() == id) {
                return new Producto (stockObj.getId(), stockObj.getNombre(),
                        stockObj.getPrecio());
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
    public void nuevoProducto(Stock p) {
        JsonParser jsonParser = new JsonParser();
        try {
            FileWriter writer;
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            objs.add(p);
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(objs, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("no se ha podido agregar el producto");
        }

    }

    public ArrayList<Stock> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Stock> inventario) {
        this.inventario = inventario;
    }
    
    public boolean guardar() {
        return inventarioController.guardarObjetos(inventario);
    }
    
    public void cargar() {
        inventario = inventarioController.cargarObjetos();
    }

}
