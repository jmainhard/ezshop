/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UtilsTest {

    static String rut;
    static int rutDigits;
    static int rutDv;

    @BeforeAll
    static void setUp(){
        rut = "20953304-9";
        rutDigits = 20953304;
    }

    // Hecho con TDD
    @Test
    void testValidarRut() {
        Assertions.assertTrue(Utils.validarRut(rut));
    }

    @Test
    void testValidarDV() {
        String DvCorrecto = "9";
        Assertions.assertEquals(DvCorrecto, Utils.validarDv(rutDigits));
    }


    
}
