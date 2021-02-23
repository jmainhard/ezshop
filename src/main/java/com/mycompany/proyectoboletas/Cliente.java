package com.mycompany.proyectoboletas;

import com.google.gson.annotations.*;
import com.mycompany.proyectoboletas.controlador.ClientesController;
import static com.mycompany.proyectoboletas.controlador.Main.askNombre;
import static com.mycompany.proyectoboletas.controlador.Main.askRut;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 * 
 *  Cliente corresponde a una clase que maneja las ventas (de una ferreteria),
 * no es un cliente comprando sino que es quién utiliza el sistema y 
 * realiza las ventas, por cada venta existe un cliente.
 * 
 */

public class Cliente {
    //Nombres Serializados para dar limpieza al JSON
    @SerializedName("Nombre Cliente")
    private String nombre;
    @SerializedName("Rut")
    private String rut;
    @SerializedName("Canasta")
    private Canasta canasta;
    @Expose
    private HistorialCliente historial;

    public Cliente() {
        this.nombre = "Nombre Indefinido";
        this.rut = "Rut Indefinido";
        this.canasta = new Canasta();
    }
    
    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.canasta = new Canasta();
    }
    
    // TODO
    // Disminuye el stock de los productos vendidos
    // Genera comprobante
    // Actualiza historial
    public boolean hacerVenta() {
        ClientesController clientesHandler = new ClientesController();
        Comprobante comprobante;
        
        if (!confirmarVenta()) {
            return false;
        }
        
        this.setRut(askRut());
        if (!clientesHandler.existeCliente(this)) {
            this.setNombre(askNombre());
        }
        
        comprobante = selectComprobante();
        
        // Asocia el historial de este cliente al comprobante seleccionado
        updateCliente(comprobante);

        
//        ? comprobante.mostrarDetalle();
//        comprobante.imprimir();
        return true;
    }
    
    public int tipoComprobante() {
        int numTipo = -1;
        Scanner teclado = new Scanner(System.in);
        
        while (numTipo < 1 || numTipo > 2) {
            numTipo = -1;
            
            System.out.println("\nSeleccione tipo de Comprobante");
            System.out.println("1 - Boleta");
            System.out.println("2 - Factura");
            try {
                numTipo = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Error: Ingrese un número "+ e);
                teclado.next();
            } catch (Exception e) {
                System.err.println("Error: "+ e);
                teclado.next();
            }
        }
        String tipo = numTipo == 1 ? " (Boleta)" : " (Factura)";
        System.out.println("\nSeleccionado: "+ numTipo+ tipo);
        
        return numTipo;
    }
    
    public Comprobante selectComprobante() {
        Comprobante comprobante;
        switch (tipoComprobante()) {
            case 1:
                return new Boleta(this);
            case 2:
                return new Factura(this);
            default:
                throw new AssertionError();
        }
    }
    
    public boolean confirmarVenta() {
        Scanner teclado = new Scanner(System.in);
        boolean confirmo = false; // CONFIRMO xd
        String respuesta = "";
        
        System.out.println("\n¿Desea finalizar esta venta?\n"
                + "esto modificará el stock\n"
                + "de los productos vendidos\n"
                + "y finalizará la sesión de venta.\n");

        System.out.print("[y - Si] ");
        System.out.println(" [n - No]");
        try {
            respuesta = teclado.next();
        } catch (Exception e) { System.err.println("Error "+ e); }
            
        if (respuesta.toLowerCase().charAt(0) == 'y') {
            confirmo = true;
        } else if (respuesta.toLowerCase().charAt(0) == 'n') {
            confirmo = false;
        }
        
        return confirmo;
    }
    
    // TODO - implementar updateIngresos
    public boolean updateIngresos() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Asocia un comprobante al historial de este cliente, si no existe lo crea
     * @param comprobante comprobante a ser asociado a este cliente
     */
    private void updateCliente(Comprobante comprobante) {
        ClientesController clientesHandler = new ClientesController();

        try {
            if (!clientesHandler.existeCliente(this)) {
                if (clientesHandler.addCliente(this)) {
                    System.out.println("\n-- Nuevo Cliente registrado --");
                    System.out.println("Rut: "+ this.getRut()+ "\n");
                }
            }
            // consigue el historial creado ahora o el que ya existia sin diferenciar
            historial = clientesHandler.getHistorialClienteComprando(this);
            
            // añade comprobante y guarda archivo de clientes
            historial.addComprobante(comprobante.getNumComprobante());
            clientesHandler.guardar();
        } catch (NullPointerException e) {
            System.err.println("Error: cliente nulo o no inicializado "+ e);
        } catch (Exception e) {
            System.err.println("Error al manejar cliente "+ e);
        }
    }
    
    // getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Canasta getCanasta() {
        return canasta;
    }

    public void setCanasta(Canasta canasta) {
        this.canasta = canasta;
    }

    @Override
    public String toString() {
        String strBuilder;
        
        strBuilder = "========== Datos Cliente ==========\n";
        strBuilder += "Nombre: "+ nombre+ "\n";
        strBuilder += "Rut: "+ rut+ "\n";
        
        return strBuilder;
    }
    
    
    
}
