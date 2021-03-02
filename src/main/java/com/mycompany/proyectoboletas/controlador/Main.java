package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Esteban E., Maximiliano C., Jorge M.
 */

public class Main {
    private static final ClientesController clientesHandler = new ClientesController();
    public static Contabilidad contabilidad = new Contabilidad();
    public static NumComprobanteController numComprobante = new NumComprobanteController();

    public static void main(String[] args) {

        contabilidad.setComprobantesTotales();
        numComprobante.setComprobantes();

        menuDePruebas();        

        
    }
    
    public static void menuDePruebas() {
        boolean repetir;
        boolean salir;
        int opcion;
        Scanner teclado = new Scanner(System.in);
        
        do {
            repetir = true;
            do {
                salir = false;
                opcion = -1;
                System.out.println("\nMenu de Ferretería [Beta]");
                System.out.println("------------< >------------");
                System.out.println("1 - Nueva Venta");
                System.out.println("2 - UNDEFINED");
                System.out.println("3 - UNDEFINED");
                System.out.println("4 - Menu Clientes");
                System.out.println("5 - Salir");
                
                try {
                    opcion = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("\nError al ingresar opción "+ e);
                    teclado.next();
                }
                
                switch (opcion) {
                    case 1:
                        menuVentas();
                        salir = false;
                        break;
                    case 2:
                        System.out.println("Unsupported operation");
                        salir = false;
                        break;
                    case 3:
                        System.out.println("Unsupported operation");
                        salir = false;
                        break;
                    case 4:
                        menuClientes();
                        salir = false;
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        salir = false;
                }
            } while (!salir);
            
            repetir = confirmarSalida();
            
        } while (repetir); // fin while menu principal

    } 
    
    public static void menuClientes() {
        boolean salir;
        int opcion;
        Scanner teclado = new Scanner(System.in);
        
            do {
                salir = false;
                opcion = -1;
                System.out.println("\n------------< Menu Clientes >------------");
                System.out.println("1 - Ver Historial de Clientes");
                System.out.println("2 - Buscar Cliente por Rut");
                System.out.println("3 - Eliminar Cliente");
                System.out.println("4 - Salir");
                
                try {
                    opcion = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("\nError al ingresar opción "+ e);
                    teclado.next();
                }
                
                switch (opcion) {
                    case 1: // Lista de clientes
                        System.out.println(clientesHandler.toString());
                        salir = false;
                        break;
                    case 2: // Buscar cliente
                        String rutBusqueda = askRut();
                        if ( clientesHandler.existeCliente(rutBusqueda) ) {
                            mostrarCliente(rutBusqueda);
                        } else {
                            System.out.println("\n-- Cliente no encontrado --");
                        }
                        salir = false;
                        break;
                    case 3: // Eliminar cliente
                        if (eliminarCliente(askRut())) {
                            System.out.println("\n-- Cliente removido exitosamente --");
                        } else {
                            System.out.println("\n-- No se pudo eliminar este cliente --"); 
                        }
                        salir = false;
                        break;
                    case 4: // Salir
                        salir = true;
                        break;
                    default:
                        salir = false;
                }
            } while (!salir); // fin wh menu clientes
            
    }

    public static void menuVentas() {
        boolean salir;
        int opcion;
        Scanner teclado = new Scanner(System.in);
        Cliente clienteComprando = new Cliente();
        Producto pdtoPrueba = new Producto(-1, "PROD UNDEFINED", 9999);
        
            do {
                salir = false;
                opcion = -1;
                System.out.println("\n------------< Nueva Venta >------------");
                System.out.println("1 - Añadir Productos");
                System.out.println("2 - Quitar productos");
                System.out.println("3 - Ver Canasta");
                System.out.println("4 - Hacer Venta");
                System.out.println("5 - Salir");
                
                try {
                    opcion = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("\nError al ingresar opción "+ e);
                    teclado.next();
                }
                
                switch (opcion) {
                    case 1: // Añadir productos
                        // Mostrar inventario
                        // seleccionar por id
                        // agregar
//                        try {
//                            throw new UnsupportedOperationException("Implement InventarioController");
//                        } catch (UnsupportedOperationException e) {
//                            System.err.println(e);
//                        }
                        try {
                            if (clienteComprando.getCanasta().addProducto(pdtoPrueba)) {
                                System.out.println("\n-- Producto Agregado a la Canasta --");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        salir = false;
                        break;
                    case 2: // Quitar productos TODO implement InventarioController
                        // Mostrar canasta
                        // seleccionar por id
                        // quitar
//                        try {
//                            throw new UnsupportedOperationException("Implement InventarioController");
//                        } catch (UnsupportedOperationException e) {
//                            System.err.println(e);
//                        }
                         try {
                             if (clienteComprando.getCanasta().getProductos().isEmpty()) {
                                 throw new CanastaVaciaException("No hay productos para eliminar");
                             } else if (clienteComprando.getCanasta().removeProducto(pdtoPrueba)) {
                                 System.out.println("\n-- Producto Removido --");
                             }
                        } catch (CanastaVaciaException e) {
                            System.err.println(e);
                        } catch (Exception e) { System.err.println("Error"+ e);}
                        salir = false;
                        break;

                    case 3: // Ver canasta
                        try {
                            if (clienteComprando.getCanasta().getProductos().isEmpty()) {
                                throw new CanastaVaciaException("No ha agregado productos a la canasta aún");
                            } else {
                                System.out.println(clienteComprando.getCanasta());
                            }
                        } catch (CanastaVaciaException e) {
                            System.err.println(e);
                        }
                        salir = false;
                        break;
                    case 4: // Hacer Venta
                        try {
                            salir = clienteComprando.hacerVenta();

                        } catch (CanastaVaciaException e) {
                            System.err.println("Error: "+ e.getMessage()+
                                    " "+ e.getClass().getCanonicalName()); 
                        }
                        break;
                    case 5: // Salir
                        salir = true;
                        break;
                    default:
                        salir = false;
                }
            } while (!salir); // fin wh menu venta
            
    } 
    
    public static boolean eliminarCliente(String rutBusqueda) {
        if (clientesHandler.removeCliente(new Cliente("", rutBusqueda))) {
            clientesHandler.guardar();
            return true;
        } else {
            return false;
        }
    }
    
    public static void mostrarCliente(String rutBusqueda) {
        Cliente clienteBuscado = new Cliente("", rutBusqueda);
        System.out.println("\n-- Cliente encontrado --");
        System.out.println(clientesHandler.getHistorialClienteComprando(clienteBuscado));
    }
    
    public static boolean confirmarSalida() {
        Scanner teclado = new Scanner(System.in);
        boolean confirmar;
        String respuesta = "";
        
        System.out.println("\n¿Desea salir?");
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
    
    public static Producto askProducto() {
        
        throw new UnsupportedOperationException("Implement InventarioController");
        
    }
    
}
