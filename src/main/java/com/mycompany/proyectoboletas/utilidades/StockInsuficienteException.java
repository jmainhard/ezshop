/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas.utilidades;

/**
 *
 * @author campo
 */
public class StockInsuficienteException extends Exception {
    
    public StockInsuficienteException(){
        super();
    }

    public StockInsuficienteException(String message){
        super(message);
    }
    
}
