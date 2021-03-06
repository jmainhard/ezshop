package com.mycompany.proyectoboletas;

import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
/**
 * @author Esteban
 */
public class Boleta extends Comprobante {

    public Boleta(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimir(){
        
        String strFecha = DateFormat.
                getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).
                format(this.getFecha());
        
        System.out.println("\n              -- COMPROBANTE --");
        System.out.println("---------------------------------------------");
        System.out.println("");
        System.out.println("Tipo: BOLETA");
        System.out.println("Num Comprobante: "+this.getNumComprobante());
        System.out.println("Fecha: " + strFecha);
        System.out.println("Nombre Cliente: "+this.getCliente().getNombre());
        System.out.println("Rut Cliente: "+this.getCliente().getRut());
        System.out.println("Productos: (Nombre / Precio / ID)");
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = this.getCliente().getCanasta().getProductos();
        for (Producto p: productos) {
            System.out.println("-> "+ p.toString());
        }
        System.out.println("Total compra: $"+this.getTotal()+" IVA inc.");
        System.out.println("");
        System.out.println("---------------------------------------------");
        
    }
    
    @Override
    public String toString() {
        return "Boleta{" + '}';
    }
    
}
