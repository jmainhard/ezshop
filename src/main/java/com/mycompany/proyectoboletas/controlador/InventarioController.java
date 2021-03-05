package com.mycompany.proyectoboletas.controlador;

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
import java.util.List;

/**
 * @author Maximiliano C., Esteban E., Jorge M.
 */
// métodos cargarStock
//         actualizarStock
//         Validador de inventario
public class InventarioController implements Imprimible {

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

    public InventarioController() {
    }

    public boolean addProducto(Producto p) {
        JsonParser jsonParser = new JsonParser();
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getId() == p.getId()) {
                    objs.get(i).setCantidad(objs.get(i).getCantidad() + 1);
                }
            }
            FileWriter writer;
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(objs, writer);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    ;
    public boolean delProducto(Producto p) throws StockInsuficienteException {
        JsonParser jsonParser = new JsonParser();
        try {
            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getId() == p.getId()) {
                    if (objs.get(i).getCantidad() == 0) {
                        throw new StockInsuficienteException("NO HAY STOCK!!");
                    } else {
                        objs.get(i).setCantidad(objs.get(i).getCantidad() - 1);
                    }

                }
            }
            FileWriter writer;
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().create();
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
