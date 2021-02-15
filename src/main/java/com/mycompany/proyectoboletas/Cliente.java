package com.mycompany.proyectoboletas;

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
    
    // X Limpia este cliente con una nueva instancia de canasta y -
    // distintos atributos para una nueva compra -> no es necesario limpieza, se maneja desde Main
    public void comprar() {
        Comprobante comprobante;
        
        // confirmar que desea comprar y explicar las consecuencias de la compra
        
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
            
            System.out.println("Seleccione tipo de Comprobante");
            try {
                System.out.println("1 - Boleta");
                System.out.println("2 - Factura");
                numTipo = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Ingrese número válido "+ e);
                teclado.next();
            }
        }
        System.out.println("Tipo comprobante: "+ numTipo);
        
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
        return "Cliente{" + "nombre=" + nombre + ", rut=" + rut + ", canasta=" + canasta + '}';
    }
    
    
    
}
