/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import org.junit.jupiter.api.*;

import static com.mycompany.proyectoboletas.utilidades.Utils.validarRut;
import static com.mycompany.proyectoboletas.utilidades.Utils.validarDv;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UtilsTest {

    static String rut_dv_1_9;
    static String rut_dv_k;
    static String rut_dv_0;

    @BeforeAll
    static void setUp() {
        rut_dv_1_9 = "89922985-1";
        rut_dv_k = "62413379-K";
        rut_dv_0 = "49773673-0";
    }

    @Test
    void testValidarRut_dv_1_9() {
        Assertions.assertTrue(validarRut(rut_dv_1_9));
    }

    @Test
    void testValidarRut_dv_k() {
        Assertions.assertTrue(validarRut(rut_dv_k));
    }

    @Test
    void testValidarRut_dv_0() {
        Assertions.assertTrue(validarRut(rut_dv_0));
    }

    @Test
    void testValidarDv_1_9() {
        char dvCorrecto;
        int rutDigits;
        dvCorrecto = rut_dv_1_9.split("-")[1].charAt(0);
        rutDigits = Integer.parseInt(rut_dv_1_9.split("-")[0]);
        Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
    }

    @Test
    void testValidarDv_k() {
        char dvCorrecto;
        int rutDigits;
        dvCorrecto = rut_dv_k.split("-")[1].charAt(0);
        rutDigits = Integer.parseInt(rut_dv_k.split("-")[0]);
        Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
    }

    @Test
    void testValidarDv_0() {
        char dvCorrecto;
        int rutDigits;
        dvCorrecto = rut_dv_0.split("-")[1].charAt(0);
        rutDigits = Integer.parseInt(rut_dv_0.split("-")[0]);
        Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
    }

}
