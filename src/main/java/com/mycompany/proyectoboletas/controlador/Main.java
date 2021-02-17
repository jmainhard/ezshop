package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.*;
import java.util.Scanner;

/**
 * @author Esteban E., Maximiliano C., Jorge M.
 */

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        
        
        // pruebas 14-02/15-02 Jorge
        Cliente cliente = new Cliente("Juan Perez", "1234567-8");
        
        System.out.println("Datos de "+ cliente.getNombre());
        System.out.println(cliente);
        System.out.println(cliente.getCanasta());
        
        cliente.comprar();
        
        // Prueba de caso compra nueva
//        String nombreCliente = teclado.nextLine();
//        String rutCliente = teclado.nextLine();
//        
//        cliente = new Cliente(nombreCliente, rutCliente);
//        
//        System.out.println("Datos de "+ cliente.getNombre()+ "(Después de compra)");
//        System.out.println(cliente);
//        System.out.println(cliente.getCanasta());
//
//        Producto producto1 = new Producto(2, "Taladro", 13, 19990);
//        Producto producto2 = new Producto(3, "Paquete de Clavos 3mm", 45, 19990);
//        Producto producto3 = new Producto(4, "Pintura", 32, 12000);
//
//        Canasta canasta = cliente.getCanasta();
//        
//        canasta.addProducto(producto1);
//        canasta.addProducto(producto2);
//        canasta.addProducto(producto3);
//        
//        // comprobando que ambas canastas (cliente y mainCanasta) tienen misma referencia a Productos
//        System.out.println(cliente.getCanasta().getProductos());
//        System.out.println(canasta.getProductos());
//        
//        System.out.println(cliente.getCanasta());
//        System.out.println(canasta);
        
        
    }
    
}
