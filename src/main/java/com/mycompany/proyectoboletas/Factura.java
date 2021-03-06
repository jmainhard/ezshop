package com.mycompany.proyectoboletas;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.util.Date;

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
        
        String strFecha = DateFormat.
            getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).
            format(this.getFecha());
        
        System.out.println("\n              -- COMPROBANTE --");
        System.out.println("---------------------------------------------");
        System.out.println("");
        System.out.println("Tipo: FACTURA");
        System.out.println("Num Comprobante: "+this.getNumComprobante());
        System.out.println("Fecha: " + strFecha);
        System.out.println("Nombre Cliente: "+this.getCliente().getNombre());
        System.out.println("Rut Cliente: "+this.getCliente().getRut());
        System.out.println("Productos: (Nombre / Precio / ID)");
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = this.getCliente().getCanasta().getProductos();
        for (Producto p: productos) {
            System.out.println("-> "+p.getNombre()+" / "+p.getPrecio()+" / " + p.getId());
        }
        double iva = this.getTotal() * impuestoAplicado;
        System.out.println("Total Neto: $"+ (this.getTotal()-iva));
        System.out.println("IVA (19%): $"+iva);
        System.out.println("Total compra: $"+this.getTotal()+" IVA inc.");
        System.out.println("");
        System.out.println("---------------------------------------------");

    }

    @Override
    public String toString() {
        return "Factura{" + '}';
    }
    
    
}
