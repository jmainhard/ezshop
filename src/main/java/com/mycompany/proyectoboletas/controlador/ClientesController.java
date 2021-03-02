package com.mycompany.proyectoboletas.controlador;

import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.Cliente;
import com.mycompany.proyectoboletas.HistorialCliente;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jorge M.
 */

public class ClientesController {
    private static ListController<HistorialCliente> clientesHandler;
    private ArrayList<HistorialCliente> clientes;
    
    public ClientesController() {
        clientesHandler = new ListController<>("clientes.json",
                new TypeToken<Collection<HistorialCliente>>(){});
        clientes = clientesHandler.cargarObjetos();
    }
    
    public boolean existeCliente(String rutClienteBuscado) throws NullPointerException {
            clientes = clientesHandler.cargarObjetos(); // actualiza
        try {
            return clientes.stream().
                anyMatch(clienteRegistrado -> 
                        (rutClienteBuscado.equals(clienteRegistrado.getRut()))
                );
        } catch (NullPointerException e) {
            System.err.println("Error: referencia nula o no inicilizada"+ e);
        }
        return false;
    }
    
    public HistorialCliente getHistorialCliente(String rutCliente) {
        for (HistorialCliente historialCliente : clientes) {
            if (rutCliente.equals(historialCliente.getRut())) {
                return historialCliente;
            }
        }
        return null;
    }
    
    public boolean addCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("Error: Cliente nulo o no inicializado");
        }
        try {
            return clientes.add(new HistorialCliente(cliente));
        } catch (NullPointerException e) {
            System.err.println(e);
        }
        return false;
    }
    
    public boolean removeCliente(String rutCliente) {
        clientes = clientesHandler.cargarObjetos();
        
        if (!existeCliente(rutCliente)) {
            return false;
        }
        try {
            return clientes.remove(this.getHistorialCliente(rutCliente));
        } catch (Exception e) {
            System.err.println("Ocurrió un error al intentar remover Cliente"+ e);
        }
        return false;
    }
    
    public void guardar() {
        clientesHandler.guardarObjetos(clientes);
    }
    
    public ArrayList<HistorialCliente> cargar() {
        return clientesHandler.cargarObjetos();
    }

    public ArrayList<HistorialCliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<HistorialCliente> clientes) {
        this.clientes = clientes;
    }
    
    public String toString() {
        clientes = clientesHandler.cargarObjetos();
        String strBuilder;
        
        strBuilder = "\n========== Clientes registrados ==========\n";
        strBuilder = clientes.stream().
                map(c -> c.toString()+ "\n").
                reduce(strBuilder, String::concat);
        
        return strBuilder;
    }
    
}
