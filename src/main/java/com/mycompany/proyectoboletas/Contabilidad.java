/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.mycompany.proyectoboletas.controlador.NumComprobanteController;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Contabilidad {
    @SerializedName("Historial Boletas")
    private ArrayList<Comprobante> comprobantesTotales = new ArrayList<>();

    public Contabilidad() {
    }

    public void setComprobantesTotales(){
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("jsons/historialComprobantes.json"));
        } catch (FileNotFoundException e) {
        }

        Contabilidad num = gson.fromJson(br,Contabilidad.class);
        this.comprobantesTotales = num.getComprobantesTotales();
    }


    public ArrayList<Comprobante> getComprobantesTotales() {
        return comprobantesTotales;
    }

    public void addComprobante(Comprobante comprobante){
        comprobantesTotales.add(comprobante);
    }

    public void reporteMensual(){
        
    }


    
}
