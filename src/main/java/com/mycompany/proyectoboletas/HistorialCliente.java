package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que controla el Historial de un Cliente
 * @author Jorge M.
 */

public class HistorialCliente {
    @SerializedName("Rut")
    private final String rut;
    @SerializedName("Nombre")
    private final String nombre;
    @SerializedName("Fecha de registro")
    private final Date fechaRegistro;
    @SerializedName("ID Comprobantes asociados")
    private final ArrayList<Integer> comprobantesAsociadosPorId;

    public HistorialCliente(Cliente cliente) {
        this.rut = cliente.getRut();
        this.nombre = cliente.getNombre();
        this.fechaRegistro = new Date();
        this.comprobantesAsociadosPorId = new ArrayList<>();
    }
    
    public boolean addComprobante(int idComprobante) {
        try {
            return comprobantesAsociadosPorId.add(idComprobante);
        } catch (NullPointerException e) {
            System.err.println("Error No se pudo asociar comprobante,"
                    + " id nula o no válida"+ e);
        }
        return false;
    }
    
    public boolean removeComprobante(Integer idComprobante) {
        try {
            comprobantesAsociadosPorId.remove(idComprobante);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error índice no válido "+ e);
        } 
        return false;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public ArrayList<Integer> getComprobantesAsociadosPorId() {
        return comprobantesAsociadosPorId;
    }

    @Override
    public String toString() {
        String strBuilder;
        
        String strFecha = DateFormat.
                getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).
                format(fechaRegistro);
        
        strBuilder = "Rut: "+ rut+ "\n";
        strBuilder += "Nombre: "+ nombre+ "\n";
        strBuilder += "Fecha de registro: "+  strFecha+ "\n";
        strBuilder += "Comprobantes asociados: "+
                comprobantesAsociadosPorId.toString()+ "\n";
        
        return strBuilder;
    }
    
    
    
    
    
    
    
    
    
    
}
