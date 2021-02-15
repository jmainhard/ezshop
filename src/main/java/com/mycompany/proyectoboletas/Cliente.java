package com.mycompany.proyectoboletas;
/**
 * @author Jorge M.
 *         Esteban E.
 *         Maximiliano C.
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
    
    
}
