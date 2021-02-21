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
    
    public boolean existeCliente(Cliente clienteComprando) throws NullPointerException {
        try {
            return clientes.stream().
                anyMatch(clienteRegistrado -> 
                        (clienteComprando.getRut().equals(clienteRegistrado.getRut()))
                );
        } catch (NullPointerException e) {
            System.err.println(e.getStackTrace());
        }
        return false;
    }
    
    public HistorialCliente getHistorialClienteComprando(Cliente clienteComprando) {
        // clientes.forEach(System.out::println); // debug
        for (HistorialCliente historialCliente : clientes) {
            if (clienteComprando.getRut().equals(historialCliente.getRut())) {
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
    
    public boolean removeCliente(Cliente cliente) {
        
        if (!existeCliente(cliente)) {
            return false;
        }
        try {
            return clientes.remove(this.getHistorialClienteComprando(cliente));
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
}
