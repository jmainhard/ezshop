package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.modelo.Comprobante;
import com.mycompany.proyectoboletas.modelo.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Esteban
 */
public class NumComprobanteController {
    @SerializedName("Num Comprobantes Generados")
    private int comprobantes;
    private static ListController<NumComprobanteController> numComprobanteHandler;

    public NumComprobanteController() {
        numComprobanteHandler = new ListController<>("numComprobantes.json",
                new TypeToken<Collection<NumComprobanteController>>(){});
    }

    // FIXME Extract class json
    public void setComprobantes() {
        NumComprobanteController num = numComprobanteHandler.cargarObjetos().get(0);
        this.comprobantes = num.getComprobantes();
    }


    public void generarNumComprobante(Comprobante comprobante) {
        comprobantes++;
        comprobante.setNumComprobante(comprobantes);
        actualizarNumero();
    }

    public int getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(int comprobantes) {
        this.comprobantes = comprobantes;
    }

    // FIXME Extract class json
    public void actualizarNumero() {
        NumComprobanteController num = new NumComprobanteController();
        num.setComprobantes(comprobantes);
        numComprobanteHandler.guardarObjetos(new ArrayList<NumComprobanteController>(Arrays.asList(num)));
    }
}
