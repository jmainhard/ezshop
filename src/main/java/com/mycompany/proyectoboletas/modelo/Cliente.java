package com.mycompany.proyectoboletas.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.*;
import com.mycompany.proyectoboletas.controlador.ClientesController;
import com.mycompany.proyectoboletas.utilidades.CanastaVaciaException;
import com.mycompany.proyectoboletas.utilidades.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.mycompany.proyectoboletas.controlador.Main.*;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 * 
 *  Cliente corresponde a una clase que maneja las ventas (de una ferreteria),
 * no es un cliente comprando sino que es quién utiliza el sistema y 
 * realiza las ventas, por cada venta existe un cliente.
 */
public class Cliente {
    private String nombre;
    private String rut;
    private Canasta canasta;

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
    
    /**
     * Realiza la venta, en términos generales disminuye el stock de los prod.
     * vendidos, genera el comprobante y actualiza el historial del cliente.
     * 
     * @return {@code true} si la venta fué realizada con éxito, {@code false}
     * caso contrario.
     * 
     * @throws CanastaVaciaException
     */
    // FIXME: método hace muchas cosas
    public boolean hacerVenta() throws CanastaVaciaException {
        ClientesController clientesHandler = new ClientesController();
        Comprobante comprobante;
        
        if (canasta.getProductos().isEmpty()) {
            throw new CanastaVaciaException("Canasta vacía");
        }
        
        if (!confirmarVenta()) {
            return false;
        }
        
        System.out.printf("%36s%n", "Ingresar Cliente");
        this.setRut(Utils.askRut());
        if (!clientesHandler.existeCliente(this.getRut())) {
            this.setNombre(Utils.askNombre());
        } else {
            System.out.println("\n-- Cliente encontrado --");
            this.setNombre(clientesHandler.getHistorialCliente(rut).getNombre());
        }

        // FIXME clase codesmells: caso default no maneja AssertionError (aunque al parecer nunca ocurra)
        comprobante = selectComprobante(); // Se crea el objeto <----
        
        // Se genera el JSON del comprobante como archivo individual
        comprobante.calcTotal();

        // FIXME Clase code smells: Revisar, variable se llama desde el Main
        numComprobante.generarNumComprobante(comprobante);
        generarComprobanteJson(comprobante);

        // Se añade a la contabilidad
        contabilidad.addComprobante(comprobante); // Añade al objeto
        addToHistory(contabilidad); // Genera el JSON

        // Asocia el historial de este cliente al comprobante
        updateCliente(comprobante);

        // FIXME code smells, revisar esto ta raro
        comprobante.imprimir();
        
        return true;
    }
    
    public int tipoComprobante() {
        int numTipo = -1;
        Scanner teclado = new Scanner(System.in);
        
        while (numTipo < 1 || numTipo > 2) {
            numTipo = -1;
            
            System.out.println("\n--< Seleccione tipo de Comprobante >--");
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
        switch (tipoComprobante()) {
            case 1:
                return new Boleta(this);
            case 2:
                return new Factura(this);
                // default No es necesario mantenerlo
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
    
    /**
     * Asocia un comprobante al historial de este cliente, si no existe lo crea
     * @param comprobante comprobante a ser asociado a este cliente
     */
    private void updateCliente(Comprobante comprobante) {
        ClientesController clientesHandler = new ClientesController();
        boolean existed = true;
        
        try {
            //Verifica si existe cliente en los archivos
            if (!clientesHandler.existeCliente(this.getRut())) {
                //Si no existe lo agrega
                if (clientesHandler.addCliente(this)) {
                    System.out.println("\n-- Nuevo Cliente registrado --");
                    System.out.println("Rut: "+ this.getRut()+ "\n");
                    existed = false;
                }
            }
            // consigue el historial creado ahora o el que ya existia indistintamente
            HistorialCliente historial = clientesHandler.getHistorialCliente(this.getRut());
            
            // añade comprobante y guarda archivo de clientes
            historial.addComprobante(comprobante.getNumComprobante());
            clientesHandler.guardar();
            
            if (existed) {
                System.out.println("\n-- Cliente Actualizado --");
            }
        } catch (NullPointerException e) {
            System.err.println("Error: cliente nulo o no inicializado "+ e);
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente "+ e);
        }
    }
    
    // Genera JSON de comprobante
    // FIXME Extract class Venta o relacionado
    public static void generarComprobanteJson(Comprobante comprobante){
        String path = "jsons/comprobantes/comprobante-"+comprobante.getNumComprobante()+".json";
        toJson(comprobante, path);
    }

    // Añade a contabilidad
    // FIXME Extract class Venta o relacionado
    public static void addToHistory(Contabilidad contabilidad){
        String path = "jsons/historialComprobantes.json";
        toJson(contabilidad, path);
    }

    // FIXME Extract class json
    private static void toJson(Object obj, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(obj);
            writer.write(jsonString);
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo");
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

        strBuilder = "Nombre cliente: "+ nombre+ "\n";
        strBuilder += "Rut cliente: "+ rut+ "\n";

        return strBuilder;
    }
}
