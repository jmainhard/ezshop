/*
 *  @author Esteban E., Jorge M.
 *  07-10-2021
 */

package com.mycompany.proyectoboletas.modelo;

import com.mycompany.proyectoboletas.controlador.ClientesController;
import org.junit.jupiter.api.*;

public class ClientesControllerTest {

    static ClientesController clientsTester;

    @BeforeAll
    static void beforeAll() {
        clientsTester = new ClientesController("clientesTest.json");
    }

    @Test
    void testExisteCliente() {
        String rutClienteBuscado = "111111-1";
        Assertions.assertTrue(clientsTester.existeCliente(rutClienteBuscado));
    }
}
