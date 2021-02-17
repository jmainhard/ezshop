package com.mycompany.proyectoboletas;

/**
 * @author Jorge M.
 */

public class Mainasd {
    public static void main(String[] args) {
        Producto producto;
        // InventarioController -> controla el json, carga, actualiza
        
        // Stock:
        // maneja el stock de los productos en inventario
        // -> aumentarCant, reducirCant
        // Productos se sacan solo por boleta
        // inventario muestra Stock (que tiene prod + cantidad)
        // Arraylist
        // Historial de comprobantes, guardar arr de comprobantes ->
        // se guarda en el mismo archivo y se muestra el que se quiere buscar,
        // filtra por numComprobante
        
        // Cliente.comprar -> reduce el stock de los productos que estan en la
        // canasta de este cliente, en el archivo JSON (InventarioController)
        
        // ActualizarStock -> filtra stream de productos.inventario con entregados
        // por parametro, por cada Objecto Stock en inventario obj.reducirCant; -> reduce segun:
        
        // método que cuenta cuantos objetos hay por id para reducir
        // la cantidad de ocurrencias que haya en Canasta.productos del mismo producto
        
    }

}
