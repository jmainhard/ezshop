package com.mycompany.proyectoboletas;

public class Main {
    public static void main(String[] args) {

        
        
        
        
        // pruebas 14-02 Jorge
        Cliente cliente = new Cliente("Juan Perez", "1234567-8");

        Producto producto1 = new Producto(2, "Taladro", 13, 19990);
        Producto producto2 = new Producto(3, "Paquete de Clavos 3mm", 45, 19990);
        Producto producto3 = new Producto(4, "Pintura", 32, 12000);

        System.out.println(cliente.getCanasta());
        System.out.println(producto3);
        
        Canasta canasta = cliente.getCanasta();
        
        System.out.println(canasta);
        
        // FIXME tira error y no tengo idea porqué
        canasta.addProducto(producto3);
        
//        cliente.getCanasta().addProducto(producto3);

   
    
    }
    
}
