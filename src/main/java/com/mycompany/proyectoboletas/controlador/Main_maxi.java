package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author campo
 */
public class Main_maxi {

    public static void main(String[] args) {
////        ArrayList<Producto> prod = new ArrayList<>();
////
////        prod.add(new Producto(0, "martillo", 5, 1000));
////        prod.add(new Producto(1, "sierra", 5, 2000));
////
////        FileWriter writer;
////        try {
////            writer = new FileWriter("src/main/java/com/mycompany/proyectoboletas/jsons/inventario.json");
////            Gson gson = new GsonBuilder().create();
////            gson.toJson(prod, writer);
////            writer.close();
////        } catch (IOException ex) {
////            System.out.println("Error al crear el archivo");
////        }

        JsonParser jsonParser = new JsonParser();
        try {

            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Producto> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Producto>>() {
            }.getType());
            System.out.println(objs.get(0).getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
