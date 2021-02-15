package com.mycompany.proyectoboletas;

/**
 * @author Esteban E; Maximiliano C; Jorge M.
 */

public class Main {
    public static void main(String[] args) {


        
        
        // pruebas 14-02 Jorge
        Cliente cliente = new Cliente("Juan Perez", "1234567-8");

        Producto producto1 = new Producto(2, "Taladro", 13, 19990);
        Producto producto2 = new Producto(3, "Paquete de Clavos 3mm", 45, 19990);
        Producto producto3 = new Producto(4, "Pintura", 32, 12000);

        Canasta canasta = cliente.getCanasta();
        
        
       
        

    
    }
    
}
