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
        
//        String strFecha = DateFormat.
//            getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).
//            format(this.getFecha());
//        
//        System.out.println("\n              -- COMPROBANTE --");
//        System.out.println("---------------------------------------------");
//        System.out.println("");
//        System.out.println("Tipo: FACTURA");
//        System.out.println("Num Comprobante: "+this.getNumComprobante());
//        System.out.println("Fecha: " + strFecha);
//        System.out.println("Nombre Cliente: "+this.getCliente().getNombre());
//        System.out.println("Rut Cliente: "+this.getCliente().getRut());
//        System.out.println("Productos: (Nombre / Precio / ID)");
//        ArrayList<Producto> productos = new ArrayList<Producto>();
//        productos = this.getCliente().getCanasta().getProductos();
//        for (Producto p: productos) {
//            System.out.println("-> "+ p.toString());
//        }
//        double iva = this.getTotal() * impuestoAplicado;
//        System.out.println("Total Neto: $"+ (this.getTotal()-iva));
//        System.out.println("IVA (19%): $"+iva);
//        System.out.println("Total compra: $"+this.getTotal()+" IVA inc.");
//        System.out.println("");
//        System.out.println("---------------------------------------------");

    }

    @Override
    public String toString() {
        String strBuilder = super.toString();
        double iva = this.getTotal() * impuestoAplicado;
        
        strBuilder += "Total Neto: $"+ (this.getTotal() - iva)+ "\n";
        strBuilder += "IVA  ("+ impuestoAplicado * 100+ "): "+ iva+ "\n";
        strBuilder += "Total compra: $"+ this.getTotal()+ " IVA inc.\n\n";
        strBuilder += "---------------------------------------------";
        
        return strBuilder;
    }
    
    
}
