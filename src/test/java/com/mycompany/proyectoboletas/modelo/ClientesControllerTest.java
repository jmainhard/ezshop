/*
 *  @author Esteban E., Jorge M.
 *  07-10-2021
 */

package com.mycompany.proyectoboletas.modelo;

import com.mycompany.proyectoboletas.controlador.ClientesController;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class ClientesControllerTest {

    static ClientesController clientsTester;
    static String rutClienteBuscado;

    @BeforeAll
    static void showClientes(){
        System.out.println("/************ Clientes inicio ***************/");
        ArrayList<HistorialCliente> clientes = clientsTester.getClientes();
        for (HistorialCliente cliente:
                clientes) {
            System.out.println(cliente.toString());
        }
    }

    @BeforeAll
    static void beforeAll() {
        rutClienteBuscado = "111111-1";
        clientsTester = new ClientesController("clientesTest2.json");
    }

    @Test
    void testExisteCliente() {
        Assertions.assertTrue(clientsTester.existeCliente(rutClienteBuscado));
    }

    @Test
    void testRemoveCliente(){
        Assertions.assertTrue(clientsTester.removeCliente(rutClienteBuscado));

    }

    @AfterAll
    static void AfterAll(){
        System.out.println("/************ Clientes final ***************/");
        ArrayList<HistorialCliente> clientes = clientsTester.getClientes();
        for (HistorialCliente cliente:
             clientes) {
            System.out.println(cliente.toString());
        }
    }
}
