package com.mycompany.proyectoboletas.modelo;

/**
 * @author Esteban
 */
public class Boleta extends Comprobante {

    public Boleta(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimir() {
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        String strBuilder = super.toString();
        
        strBuilder += "Total compra: $"+ this.getTotal()+ " IVA inc.\n\n";
        strBuilder += "---------------------------------------------\n";
        
        return strBuilder;
    }
    
}
