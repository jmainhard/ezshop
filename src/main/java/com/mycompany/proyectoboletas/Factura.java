package com.mycompany.proyectoboletas;

import com.google.gson.annotations.SerializedName;

/**
 * @author Esteban
 */
public class Factura extends Comprobante {
    @SerializedName("Impuesto aplicado")
    private final double impuestoAplicado; 

    public Factura(Cliente cliente) {
        super(cliente);
        this.impuestoAplicado = 0.19;
    }

    @Override
    public void imprimir() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String strBuilder = super.toString();
        double iva = this.getTotal() * impuestoAplicado;
        
        strBuilder += "Total Neto: $"+ (this.getTotal() - iva)+ "\n";
        strBuilder += "IVA  ("+ impuestoAplicado * 100+ "%): "+ iva+ "\n";
        strBuilder += "Total compra: $"+ this.getTotal()+ " IVA inc.\n\n";
        strBuilder += "---------------------------------------------";
        
        return strBuilder;
    }
    
    
}
