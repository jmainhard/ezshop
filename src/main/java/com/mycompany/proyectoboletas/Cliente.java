package com.mycompany.proyectoboletas;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jorge M., Esteban E., Maximiliano C.
 */

public class Cliente {
    private String nombre;
    private String rut;
    private Canasta canasta;
    
    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.canasta = new Canasta();
    }
    
    // TODO
    // Disminuye el stock de (uno o más) productos vendidos
    // Genera comprobante 
    
    // no es necesario limpieza, se maneja desde Main
    public void comprar() {
        Comprobante comprobante;
        
        // confirmar que desea comprar y explicar las consecuencias de la compra
        // Desea comprar? esto modificará el stock de los productos en canasta y finalizará la sesión de venta
        
        switch (tipoComprobante()) {
            case 1:
                comprobante = new Boleta(this);
                break;
            case 2:
                comprobante = new Factura(this);
                break;
            default:
                throw new AssertionError();
        }
        
//        ? comprobante.mostrarDetalle();
//        comprobante.imprimir();
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
