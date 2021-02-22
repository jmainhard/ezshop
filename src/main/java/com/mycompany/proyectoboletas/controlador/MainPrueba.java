package com.mycompany.proyectoboletas.controlador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.proyectoboletas.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Esteban
 */
public class MainPrueba {
    public static void main(String[] args) {
        NumComprobanteController numCont = new NumComprobanteController();
        numCont.setComprobantes();
        Producto p1 = new Producto(1,"Martillo",5000);
        Producto p2 = new Producto(2,"Sierra",4000);
        Producto p3 = new Producto(3,"Alicate",2500);
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        
        Canasta canasta = new Canasta(productos);
        
        Cliente cliente = new Cliente("Esteban Esparza", "20953304-9");
        cliente.setCanasta(canasta);
        
        Boleta comprobante = new Boleta(cliente);
        comprobante.calcTotal();
        numCont.generarNumComprobante(comprobante);
        
        generarComprobanteJson(comprobante);
        
        
        
        
    }
    //Crear archivo JSON 
    public static void generarComprobanteJson(Comprobante comprobante){
        String path = "jsons/comprobante-"+comprobante.getNumComprobante()+".json";
        
        FileWriter writer;
        try {
            writer = new FileWriter(path);
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
