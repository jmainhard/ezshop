package com.mycompany.proyectoboletas;

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
        System.out.println("Num Comprobante: "+this.getNumComprobante());
        System.out.println("Fecha: "+this.getFecha());
        System.out.println("Nombre Cliente: "+this.getCliente().getNombre());
        System.out.println("Rut Cliente: "+this.getCliente().getRut());
        System.out.println("Productos: (Nombre / Precio / ID)");
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = this.getCliente().getCanasta().getProductos();
        for (Producto p: productos) {
            System.out.println("-> "+p.getNombre()+" / "+p.getPrecio()+" / " + p.getId());
        }
        System.out.println("Total compra: $"+this.getTotal()+" IVA inc.");
        
    }
    
    @Override
    public String toString() {
        return "Boleta{" + '}';
    }
    
}
