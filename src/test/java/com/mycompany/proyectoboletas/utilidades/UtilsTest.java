/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;

public class UtilsTest {

    // Hecho con TDD
    @Test
    void testValidarRut() {
        String rutToValidate = "65157593-1";
        Assertions.assertTrue(Utils.validarRut(rutToValidate));
    }


    
}
