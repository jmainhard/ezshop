package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Esteban E., Maximiliano C., Jorge M.
 */

public class Main {
    public static ClientesController clientesController = new ClientesController();
    public static Contabilidad contabilidad = new Contabilidad();
    public static NumComprobanteController numComprobante = new NumComprobanteController();
    public static InventarioController inventarioController = new InventarioController();

    public static void main(String[] args) {

        contabilidad.setComprobantesTotales();
        numComprobante.setComprobantes();

        menuPrincipal();
        
    }
    
    public static void menuPrincipal() {
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
                System.out.println("2 - Buscar Comprobante");
                System.out.println("3 - Reporte de ingresos");
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
                        buscarComprobante();
                        salir = false;
                        break;
                    case 3:
                        contabilidad.reporteIngresos();
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
                System.out.println("1 - Ver historial de Clientes");
                System.out.println("2 - Buscar Cliente");
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
                        System.out.println(clientesController.toString());
                        break;
                    case 2: // Buscar cliente
                        System.out.println("   Búsqueda de Cliente");
                        String rutCliente = askRut();
                        if (clientesController.existeCliente(rutCliente)) {
                            System.out.println("\n-- Cliente encontrado --");
                            System.out.println(clientesController.getHistorialCliente(rutCliente));
                        } else {
                            System.out.println("\n-- Cliente no encontrado --");
                        }
                        break;
                    case 3: // Eliminar cliente
                        System.out.println("   Remover Cliente");
                        if (clientesController.removeCliente(askRut())) {
                            System.out.println("\n-- Cliente removido exitosamente --");
                            clientesController.guardar();
                        } else {
                            System.out.println("\n-- No se pudo eliminar este cliente --"); 
                        }
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
        int opcion, idProducto;
        Scanner teclado = new Scanner(System.in);
        Cliente clienteComprando = new Cliente();
        InventarioController inventarioVolatil = inventarioController;
        
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
                        boolean existe;
                        inventarioVolatil.imprimir();
                        
                        System.out.println("   Añadir Producto");
                        idProducto = askIdProducto();
                        
                        existe = inventarioVolatil.existeProducto(idProducto);
                        
                        if (existe) {
                            boolean agregado = false;
                            boolean stockReducido = false;
                            Producto pdctoAgregado;
                            
                            try {
                                pdctoAgregado = inventarioVolatil.getProducto(idProducto);
                                stockReducido = inventarioVolatil.reducirStock(idProducto);
                                
                                agregado = clienteComprando.getCanasta().addProducto(pdctoAgregado);
                            } catch (StockInsuficienteException e) {
                                System.err.println(
                                        "Error: "+
                                                e.getClass().getSimpleName()+
                                                ": "+
                                                e.getMessage()
                                );
                            } catch (Exception e) {
                                System.err.println(
                                        "Error al agregar producto: "+ e
                                );
                            }
                            
                            if (agregado && stockReducido) {
                                System.out.print(
                                        "\n-- Inventario Actualizado --"
                                );
                                inventarioVolatil.imprimir();
                                
                                System.out.println(
                                        "\n-- Producto Agregado a la Canasta --"
                                );
                                System.out.println(clienteComprando.getCanasta());
                            } else {
                                System.out.println("\n-- Producto no agregado --");
                            }
                        }
                        salir = false;
                        break;
                    case 2: // Quitar productos 
                         try {
                             if (clienteComprando.getCanasta().getProductos().isEmpty()) {
                                 throw new CanastaVaciaException("No hay productos para eliminar");
                             } else {
                                 System.out.println(clienteComprando.getCanasta());
                                 System.out.println("   Remover Producto");
                                 int idProductoRemover = askIdProducto();
                                 if (clienteComprando.getCanasta().removeProducto(idProductoRemover)) {
                                     System.out.println("\n-- Producto Removido --");
                                     System.out.println(clienteComprando.getCanasta());
                                     inventarioVolatil.aumentarStock(idProductoRemover);
                                 } else {
                                     System.out.println("\n-- Producto no removido --");
                                 }
                             }
                        } catch (CanastaVaciaException e) {
                            System.err.println(
                                    "Error: "+
                                            e.getClass().getSimpleName()+
                                            ": "+
                                            e.getMessage()
                            );
                        } catch (Exception e) { 
                            System.err.println("Error: "+ e);
                        }
                        salir = false;
                        break;

                    case 3: // Ver canasta
                        try {
                            if (clienteComprando.getCanasta().getProductos().isEmpty()) {
                                throw new CanastaVaciaException("La canasta no tiene productos");
                            } else {
                                System.out.println(clienteComprando.getCanasta());
                            }
                        } catch (CanastaVaciaException e) {
                            System.err.println(e.getMessage() + " "+
                                    e.getClass().getSimpleName());
                        }
                        salir = false;
                        break;
                    case 4: // Hacer Venta
                        try {
                            salir = clienteComprando.hacerVenta();
                            if (salir) {
                                inventarioController.guardar();
                            }
                        } catch (CanastaVaciaException e) {
                            System.err.println(
                                    "Error: "+
                                            e.getClass().getSimpleName()+
                                            ": "+
                                            e.getMessage()
                            ); 
                        }
                        break;
                    case 5: // Salir
                        inventarioVolatil.cargar();
                        salir = true;
                        break;
                    default:
                        salir = false;
                }
            } while (!salir); // fin wh menu venta
            
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

    public static void buscarComprobante(){
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        int num;
        while (!salir) {
            System.out.println("\nIngrse tipo de comprobante");
            System.out.println("------------< >------------");
            System.out.println("1. Factura");
            System.out.println("2. Boleta");
            System.out.println("3. Salir");
            try {
                System.out.println("Escribe una opción");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        getNumeroComprobate(opcion);
                        salir = true;
                        break;
                    case 2:
                        getNumeroComprobate(opcion);
                        salir = true;
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo valores entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor ingresado no valido.");
                teclado.next();
            }
        }
    }
    
    public static void getNumeroComprobate(int tipo){
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion = 1;
        int num;
        while (!salir) {
            try {
                System.out.println("\nIngrese el número de comprobante");
                num = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        contabilidad.getComprobante(tipo, num);
                        salir = true;
                        break;

                    default:
                        System.out.println("Solo valores entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor ingresado no valido.");
                teclado.next();
            }
        }
    }

    public static String askNombre() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n   < Ingrese Nombre >");
        return teclado.nextLine();
    }
    
    public static String askRut() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("   < Ingrese Rut >");
        return teclado.next();
    }
    
    public static int askIdProducto() {
        int id = -1;
        Scanner teclado = new Scanner(System.in);
        int inventarioSize = inventarioController.getInventario().size();
        
        while (id < 1 || id > inventarioSize) {
            id = -1;
            System.out.println("   < Ingrese ID >");
            try {
                id = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Error: Ingrese un número "+ e);
                teclado.next();
            } catch (Exception e) {
                System.err.println("Error: "+ e);
                teclado.next();
            }
        }
        System.out.println("\nSeleccionado: "+ id);
        
        return id;
    }
    
}
