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
    @SerializedName("Historial Facturas")
    private ArrayList<Factura> facturasTotales = new ArrayList<>();
    @SerializedName("Historial Boletas")
    private ArrayList<Boleta> boletasTotales = new ArrayList<>();

    public Contabilidad() {
    }

    // Se debe llamar cada vez que se instancia al objeto, almenos una vez en la ejecución
    // Lee el json y restablece los datos de los Comprobantes
    public void setComprobantesTotales(){
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("jsons/historialComprobantes.json"));
        } catch (FileNotFoundException e) {
        }

        Contabilidad num = gson.fromJson(br,Contabilidad.class);
        this.facturasTotales = num.getFacturasTotales();
        this.boletasTotales = num.getBoletasTotales();
    }


    public ArrayList<Factura> getFacturasTotales() {
        return facturasTotales;
    }

    public ArrayList<Boleta> getBoletasTotales() {
        return boletasTotales;
    }
    //Añade un comprobante al historial, separa por tipo (FACTURA O BOLETA)
    public void addComprobante(Comprobante comprobante){
        if (comprobante.getClass() == Factura.class){
            facturasTotales.add((Factura) comprobante);
        }
        if(comprobante.getClass() == Boleta.class){
            boletasTotales.add((Boleta) comprobante);
        }
    }

    public void reporteMensual(){
        double ingresos = 0;
        int cont = 0;
        for(Factura f : facturasTotales){
            ingresos+= f.getTotal();
            cont++;
        }

        for(Boleta b : boletasTotales){
            ingresos+= b.getTotal();
            cont++;
        }
        System.out.println("Cantidad de ventas realizada: "+cont);
        System.out.println("Los ingresos historicos son: $"+ingresos);
    }


    
}
