package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.StockInsuficienteException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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
    public void imprimir() {
        JsonParser jsonParser = new JsonParser();
        try {

            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            for (int i = 0; i < objs.size(); i++) {
                System.out.println("ID: " + objs.get(i).getId());
                System.out.println("nombre: " + objs.get(i).getNombre());
                System.out.println("precio: $" + objs.get(i).getPrecio());
                System.out.println("cantidad disponible: " + objs.get(i).getCantidad());
            }
        } catch (IOException e) {
            System.out.println("error al leer el archivo");
        }
    }
    
    public boolean existeProducto(int id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            ArrayList<Stock> objs = new Gson().fromJson(br, new TypeToken<List<Stock>>() {
            }.getType());
            return objs.stream().anyMatch(producto -> id == producto.getId());
        } catch (IOException e) {
            System.err.println("Error al consultar producto "+ e);
        }
        return false;
    }
    
    public Producto getProducto(int id) throws StockInsuficienteException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            ArrayList<Stock> objs = new Gson().fromJson(br, new TypeToken<List<Stock>>() {
            }.getType());
            
            for (Stock pdcto : objs) {
                if (pdcto.getCantidad() == 0) {
                    throw new StockInsuficienteException("NO HAY STOCK!!");
                } else if (pdcto.getId() == id) {
                    return pdcto;
                }
            }
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            System.err.println("Error al leer inventario "+ e);
        }
        
        return null;
    }

    public boolean aumentarStock(Producto p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            ArrayList<Stock> objs = new Gson().fromJson(br, new TypeToken<List<Stock>>() {
            }.getType());
            
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getId() == p.getId()) {
                    objs.get(i).setCantidad(objs.get(i).getCantidad() + 1);
                }
            }
            
            FileWriter writer;
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(objs, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al manejar inventario "+ e);
        }
        return true;
    }

    ;
    public boolean reducirStock(int id) throws StockInsuficienteException {
        JsonParser jsonParser = new JsonParser();
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getId() == id) {
                    if (objs.get(i).getCantidad() == 0) {
                        throw new StockInsuficienteException("NO HAY STOCK!!");
                    } else {
                        objs.get(i).setCantidad(objs.get(i).getCantidad() - 1);
                    }

                }
            }
            FileWriter writer;
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(objs, writer);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
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
            Gson gson = new GsonBuilder().create();
            gson.toJson(objs, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("no se ha podido agregar el producto");
        }

    }

}
