/*
 *  @author Esteban E., Jorge M.
 *  07-10-2021
 */

package com.mycompany.proyectoboletas.modelo;

import com.mycompany.proyectoboletas.controlador.ClientesController;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientesControllerTest {

    static ClientesController clientsTester;
    static String rutClienteBuscado;

    @BeforeAll
    static void showClientes(){
        System.out.println("/************ Clientes inicio ***************/");
        ArrayList<HistorialCliente> clientes = clientsTester.getClientes();
        System.out.println(clientsTester.toString());
    }

    @BeforeAll
    static void beforeAll() {
        rutClienteBuscado = "22222222-2";
        clientsTester = new ClientesController("clientesTest.json");
    }

    @Test
    void testExisteCliente() {
        assertTrue(clientsTester.existeCliente(rutClienteBuscado));
    }

    @Test
    void testRemoveCliente() {
        assertTrue(clientsTester.removeCliente(rutClienteBuscado));
    }

    @AfterAll
    static void AfterAll(){
        System.out.println("/************ Clientes final ***************/");
        ArrayList<HistorialCliente> clientes = clientsTester.getClientes();
        System.out.println(clientsTester.toString());
    }
}
