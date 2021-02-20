/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Esteban
 */
public class NumComprobanteController {
    @SerializedName("Num Comprobantes Generados")
    private int comprobantes;

    public NumComprobanteController() {
        
        
    }

    public void setComprobantes() {
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("jsons/numComprobantes.json"));
        } catch (FileNotFoundException e) {
        }
        
        NumComprobanteController num = gson.fromJson(br,NumComprobanteController.class);
        this.comprobantes = num.getComprobantes();
    }
    
    

    public void generarNumComprobante(Comprobante comprobante){
        comprobantes++;
        comprobante.setNumComprobante(comprobantes);
        generarJson();
        
    }

    public int getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(int comprobantes) {
        this.comprobantes = comprobantes;
    }
    
    public void generarJson(){
        NumComprobanteController num = new NumComprobanteController();
        num.setComprobantes(comprobantes);
        FileWriter writer;
        try {
            writer = new FileWriter("jsons/numComprobantes.json");
            //PrettyPrint para dar format al JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(num);
            writer.write(jsonString);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
        }

    }
}
