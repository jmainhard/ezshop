package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author campo
 */
public class Main_maxi {

    public static void main(String[] args) {
        ArrayList<Producto> prod = new ArrayList<>();

        prod.add(new Stock(0, "martillo", 1000, 5));
        prod.add(new Stock(1, "llave inglesa", 1500, 2));

        FileWriter writer;
        try {
            writer = new FileWriter("jsons/inventario.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(prod, writer);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
        }

        JsonParser jsonParser = new JsonParser();
        try {

            BufferedReader br = new BufferedReader(new FileReader("jsons/inventario.json"));
            JsonElement jsonElement = jsonParser.parse(br);
            ArrayList<Stock> objs = new Gson().fromJson(jsonElement, new TypeToken<List<Stock>>() {
            }.getType());
            System.out.println(objs.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
