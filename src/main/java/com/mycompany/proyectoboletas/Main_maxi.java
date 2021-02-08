/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author campo
 */
public class Main_maxi {

    public static void main(String[] args) {
        ArrayList<Producto> prod = new ArrayList<>();

        prod.add(new Producto(0, "martillo", 5, 1000));
        prod.add(new Producto(1, "sierra", 5, 2000));

        FileWriter writer;
        try {
            writer = new FileWriter("src/main/java/com/mycompany/proyectoboletas/jsons/inventario.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(prod, writer);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
        }
    }

}
