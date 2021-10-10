/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UtilsTest {

    // Hecho con TDD
    @ParameterizedTest
    @ValueSource(strings = { "57179825-5", "48544323-1", "29597718-3" })
    void testValidarRut(String rut) {
        Assertions.assertTrue(Utils.validarRut(rut));
    }


    
}
