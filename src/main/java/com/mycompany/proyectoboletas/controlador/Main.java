package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Esteban E., Maximiliano C., Jorge M.
 */

public class Main {
    private static final Cliente cliente = new Cliente();
    
    public static void main(String[] args) {
        menuDePruebas();        

//        Producto p = new Producto(1, "Martillo", 22222);
//        Producto p1 = new Producto(2, "Sierra", 19990);
//        Producto p2 = new Producto(3, "Sierra Eléctrica", 45990);
//        Stock s = new Stock(p, 6);
//        Stock s1 = new Stock(p1, 14);
//        Stock s2 = new Stock(p2, 13);
//        
//        ArrayList<Stock> inventario = new ArrayList<>();
//        inventario.add(s);
//        inventario.add(s1);
//        inventario.add(s2);
//        
//        inventario.forEach(System.out::println);
//        
        // pruebas 20-02 Jorge
//        Cliente cliente = new Cliente("Juan Perez", "1234567-8");
        
//        cliente.hacerVenta();
        
//        ClientesController clientesHandler = new ClientesController();
//        Cliente cliente1 = new Cliente("Douglas Adams", "8765432-1");
//        
//        System.out.println(clientesHandler.existeCliente(cliente1));
        
//        if (clientesHandler.removeCliente(cliente1)) {
//            System.out.println("\n-- Cliente removido --");
//            System.out.println("Rut: "+ cliente1.getRut()+ "\n");
//            clientesHandler.guardar();
//        }
        
//        System.out.println(clientesHandler.existeCliente(cliente1));

        
//        cliente1.hacerVenta();
        
//        System.out.println(clientesHandler.existeCliente(cliente1));

        
        // pruebas 14-02/15-02 Jorge
//        Cliente cliente = new Cliente("Juan Perez", "1234567-8");
//        
//        System.out.println("Datos de "+ cliente.getNombre());
//        System.out.println(cliente);
//        
//        Producto producto1 = new Producto(2, "Taladro", 13, 19990);
//        Producto producto2 = new Producto(3, "Paquete de Clavos 3mm", 45, 19990);
//        Producto producto3 = new Producto(4, "Pintura", 32, 12000);
//
//        cliente.getCanasta().addProducto(producto1);
//        cliente.getCanasta().addProducto(producto2);
//        cliente.getCanasta().addProducto(producto3);
//        
//        System.out.println(cliente.getCanasta());
        
//        cliente.hacerVenta();
        
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
//        
//
//        Canasta canasta = cliente.getCanasta();
//        

//        
//        // comprobando que ambas canastas (cliente y mainCanasta) tienen misma referencia a Productos
//        System.out.println(cliente.getCanasta().getProductos());
//        System.out.println(canasta.getProductos());
//        
//        System.out.println(cliente.getCanasta());
//        System.out.println(canasta);
        
        
    }
    
    public static void menuDePruebas() {
        ClientesController clientesHandler = new ClientesController();
        boolean repetir;
        boolean salir;
        int opcion;
        Scanner teclado = new Scanner(System.in);
        
        do {
            repetir = true;
            do {
                salir = false;
                opcion = -1;
                System.out.println("\nMenu de Ferretería v. Alpha");
                System.out.println("------------< >------------");
                System.out.println("1 - Hacer Venta");
                System.out.println("2 - Ver Clientes");
                System.out.println("3 - Salir");
                
                try {
                    opcion = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("\nError al ingresar opción "+ e);
                    teclado.next();
                }
                
                switch (opcion) {
                    case 1:
                        // Aquí irian submenus como menuVentas(); este tendría p. ej. -> 
                        // 1 - añadir productos a canasta 
                        // 2 - quitar productos
                        // 3 - hacer venta
                        // 4 - Salir
                        // por ahora lo estoy utilizando para probar funcionalidades aisladas
                        cliente.hacerVenta();
                        salir = false;
                        break;
                    case 2:
                        // menuClientes(); (sub menu)
                        System.out.println(clientesHandler.toString());
                        salir = false;
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        salir = false;
                }
            } while (!salir);
            
            repetir = confirmarSalida();
            
        } while (repetir); // fin while menu principal

    } 
    
    public static boolean confirmarSalida() {
        Scanner teclado = new Scanner(System.in);
        boolean confirmar;
        String respuesta = "";
        
        System.out.println("\nDesea salir?");
        System.out.print("\n[y - Si] ");
        System.out.println(" [n - No]");

        try {
            respuesta = teclado.next();
        } catch (Exception e) { System.err.println("Error "+ e); }
        
        confirmar = respuesta.toLowerCase().charAt(0) == 'y';

        return !confirmar;
    }
    
    // FIXME añadir control de excepciones
    public static String askNombre() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n< Ingrese Nombre Cliente >");
        return teclado.nextLine();
    }
    
    // FIXME añadir control de excepciones
    public static String askRut() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n< Ingrese Rut Cliente >");
        return teclado.next();
    }
    
}
