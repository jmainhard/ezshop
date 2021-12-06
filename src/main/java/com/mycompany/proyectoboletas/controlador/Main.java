package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.modelo.*;
import com.mycompany.proyectoboletas.utilidades.CanastaVaciaException;
import com.mycompany.proyectoboletas.utilidades.StockInsuficienteException;
import com.mycompany.proyectoboletas.utilidades.Utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

/**
 * @author Esteban E., Maximiliano C., Jorge M.
 */

public class Main {
    public static ClientesController clientesController = new ClientesController();
    public static Contabilidad contabilidad = new Contabilidad();
    public static NumComprobanteController numComprobante = new NumComprobanteController();
    public static InventarioController inventarioController = new InventarioController();
    public static Scanner teclado = new Scanner(System.in);
    private static FileHandler fileLog;
    public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void loggerSetup() {
        try {
            fileLog = new FileHandler("log.log");
        } catch (SecurityException | IOException e) {
            logger.warning("Filehandler not functional");
        }
        logger.addHandler(fileLog);
        // setear formato del log, https://stackoverflow.com/questions/194765, https://www.logicbig.com/tutorials/core-java-tutorial/logging/customizing-default-format.html
        // dir: JAVA_HOME/conf/logging.properties
        // java.util.logging.SimpleFormatter.format=[%1$tF %1$tT] [%4$-7s] %5$s %n
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileLog.setFormatter(formatterTxt);
    }

    public static void main(String[] args) throws IOException {
        // init app
        loggerSetup();
        logger.info("Aplicación Ezshop iniciada");

        logger.info("Cargando historial de contabilidad dir: jsons/historialComprobantes.json");
        contabilidad.setComprobantesTotales("jsons/historialComprobantes.json");
        logger.info("Información cargada correctamente");

        logger.info("Cargando cantidad de comprobantes dir: jsons/numComprobantes.json");
        numComprobante.setComprobantes();
        logger.info("Información cargada correctamente");

        System.out.printf("%n%25s%n", "Hola!");
        System.out.println("> Ingrese opciones con las teclas numéricas <\n");
        System.out.printf("%35s", "| Boletero de Ferretería |");

        menuPrincipal();

        System.out.printf("%27s%n", "Gracias! :)");
        logger.info("Aplicación Ezshop finalizada");
    }
    
    public static void menuPrincipal() {
        boolean repetir;
        boolean salir;
        int opcion;
        
        do {
            repetir = true;
            do {
                salir = false;
                opcion = -1;
                System.out.println("\n------------< Menu Principal >------------");
                System.out.println("1 - Nueva Venta");
                System.out.println("2 - Buscar Comprobante");
                System.out.println("3 - Reporte de ingresos");
                System.out.println("4 - Menu Clientes");
                System.out.println("5 - Aumentar stock de un producto");
                System.out.println("6 - Salir");
                
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
                        System.out.println("\n-- Producto a aumentar --");
                        if ( inventarioController.aumentarStock(Utils.askIdProducto())) {
                            inventarioController.guardar();
                            System.out.println("-- Aumentado con éxito --");
                        } else {
                            System.out.println("No se pudo aumentar el stock");
                        }
                        salir = false;
                        break;
                    case 6:
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
                        clientesController.imprimir();
                        break;
                    case 2: // Buscar cliente
//                        System.out.println("   Búsqueda de Cliente");
                        System.out.printf("%39s%n", "Búsqueda de Cliente");
                        String rutCliente = Utils.askRut();
                        if (clientesController.existeCliente(rutCliente)) {
                            System.out.println("\n-- Cliente encontrado --");
                            System.out.println(clientesController.getHistorialCliente(rutCliente));
                        } else {
                            System.out.println("\n-- Cliente no encontrado --");
                        }
                        break;
                    case 3: // Eliminar cliente
                        System.out.printf("%39s%n", "Remover Cliente");
                        if (clientesController.removeCliente(Utils.askRut())) {
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

    // FIXME clase de codesmells con compañeros:
    // método bastante largo, revisar opción de separar
    public static void menuVentas() {
        boolean salir;
        int opcion, idProducto;
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
                        idProducto = Utils.askIdProducto();
                        
                        existe = inventarioVolatil.existeProducto(idProducto);
                        
                        if (existe) {
                            boolean agregado = false;
                            boolean stockReducido = false;
                            Producto pdctoAgregado;
                            
                            try {
                                pdctoAgregado = inventarioVolatil.getProducto(idProducto);
                                stockReducido = inventarioVolatil.reducirStock(idProducto);
                                
                                agregado = clienteComprando.getCanasta().addProducto(pdctoAgregado);
                                logger.info("Producto agregado id: " + pdctoAgregado.getId()
                                        + " nombre: " + pdctoAgregado.getNombre());
                            } catch (StockInsuficienteException e) {
                                System.err.println( "Error: "+ e.getClass().getSimpleName()+ ": "+ e.getMessage());
                                logger.warning("Stock insuficiente id: " + idProducto);
                            } catch (Exception e) {
                                String msg = "Error al agregar producto: " + e;
                                System.err.println(msg);
                                logger.warning(msg);
                            }
                            
                            if (agregado && stockReducido) {
                                System.out.print("-- Inventario Actualizado --");
                                inventarioVolatil.imprimir();
                                
                                System.out.println("\n-- Producto Agregado a la Canasta --");
                                System.out.println(clienteComprando.getCanasta());

                                logger.info("Inventario temporal actualizado");
                            } else {
                                System.out.println("\n-- Producto no agregado --");
                                logger.info("Stock no reducido id: " +  idProducto);
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
                                 int idProductoRemover = Utils.askIdProducto();
                                 if (clienteComprando.getCanasta().removeProducto(idProductoRemover)) {
                                     System.out.println("\n-- Producto Removido --");
                                     System.out.println(clienteComprando.getCanasta());
                                     inventarioVolatil.aumentarStock(idProductoRemover);
                                     logger.info("Producto removido id: " + idProductoRemover);
                                 } else {
                                     System.out.println("\n-- Producto no removido --");
                                     logger.info("Producto no removido id: " + idProductoRemover);
                                 }
                             }
                        } catch (CanastaVaciaException e) {
                             String msg = "Error: " + e.getClass().getSimpleName() + ": " + e.getMessage();
                            System.err.println(msg);
                            logger.warning(msg);
                        } catch (Exception e) {
                            System.err.println("Error: " + e);
                            logger.warning("Error: " + e);
                        }
                        salir = false;
                        break;
                    case 3: // Ver canasta
                        try {
                            if (clienteComprando.getCanasta().getProductos().isEmpty()) {
                                throw new CanastaVaciaException("La canasta no tiene productos");
                            } else {
                                System.out.println(clienteComprando.getCanasta());
                                logger.info("Ver canasta venta");
                            }
                        } catch (CanastaVaciaException e) {
                            String msg = e.getMessage() + " " + e.getClass().getSimpleName();
                            System.err.println(msg);
                            logger.warning(msg);
                        }
                        salir = false;
                        break;
                    case 4: // Hacer Venta
                        try {
                            salir = clienteComprando.hacerVenta();
                            logger.info("Hacer venta cliente: " + clienteComprando.getRut());

                            // no estoy seguro de si esta condición se cumple alguna vez
                            if (salir) {
                                inventarioController.guardar();
                                logger.info("Inventario actualizado dir: jsons/inventario.json");
                            }
                        } catch (CanastaVaciaException e) {
                            String msg = "Error: " + e.getClass().getSimpleName() + ": " + e.getMessage();
                            System.err.println(msg);
                            logger.warning(msg);
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
        boolean salir = false;
        while (!salir) {
            System.out.println("\nIngrese tipo de comprobante");
            System.out.println("------------< >------------");
            System.out.println("1. Factura");
            System.out.println("2. Boleta");
            System.out.println("3. Salir");
            try {
                System.out.println("Escribe una opción");
                int opcion = teclado.nextInt();
                if(!(opcion == 1 || opcion ==2 || opcion ==3)){
                    System.out.println("Solo valores entre 1 y 3");
                }else{
                    if(opcion==3){
                        salir = true;
                    }else{
                        getComprobante(opcion);
                        salir = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor ingresado no valido. Solo valores númericos.");
                teclado.next();
            }
        }
    }

    public static void getComprobante(int tipo){
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("Ingrese el número de comprobante");
                int num = teclado.nextInt();
                if(tipo == 1){
                    contabilidad.getFactura(num);
//                    logger.info("Obtener factura num: " + num);
                }
                if(tipo == 2){
                    contabilidad.getBoleta(num);
//                    logger.info("Obtener boleta num: " + num);
                }
                salir = true;
            } catch (InputMismatchException e) {
                String msg = "Valor ingresado no válido " + e;
                System.out.println(msg);
                logger.warning("Obtener comprobante. " + msg);
                teclado.next();
            }
        }
    }

}
