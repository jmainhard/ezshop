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
 * @author Esteban
 */
public class MainPrueba {
    public static void main(String[] args) {
        
        Producto p1 = new Producto(1,"Martillo",10,5000);
        Producto p2 = new Producto(2,"Sierra",10,4000);
        Producto p3 = new Producto(3,"Alicate",10,2500);
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        
        Canasta canasta = new Canasta(productos);
        
        Cliente cliente = new Cliente("Esteban Esparza", "20953304-9");
        cliente.setCanasta(canasta);
        
        Comprobante comprobante = new Comprobante(cliente);
        comprobante.calcTotal();
        generarComprobanteJson(comprobante);
        
        
    }
    //Crear archivo JSON 
    public static void generarComprobanteJson(Comprobante comprobante){
        FileWriter writer;
        try {
            writer = new FileWriter("src/main/java/com/mycompany/proyectoboletas/jsons/comprobante.json");
            //PrettyPrint para dar format al JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(comprobante);
            writer.write(jsonString);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
        }

    }
    
    
}
