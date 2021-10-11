/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Nested
    class Un_rut_es_valido {

        @DisplayName("Si tiene sólo digitos y dígito verificador")
        @ParameterizedTest(name = "Por ejemplo, {0} es un rut válido.")
        @ValueSource(strings = { "73798773-6", "93831953-7", "44866941-6" })
        void testIfOnlyDigits(String rutWithLetters) {
            Assertions.assertTrue(validarRut(rutWithLetters));
        }

        @Nested
        class Y_su_digito_verificador {
            @Test
            void esta_entre_1_y_9() {
                char dvCorrecto;
                int rutDigits;
                dvCorrecto = rut_dv_1_9.split("-")[1].charAt(0);
                rutDigits = Integer.parseInt(rut_dv_1_9.split("-")[0]);
                Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
            }

            @Test
            void es_k() {
                char dvCorrecto;
                int rutDigits;
                dvCorrecto = rut_dv_k.split("-")[1].charAt(0);
                rutDigits = Integer.parseInt(rut_dv_k.split("-")[0]);
                Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
            }

            @Test
            void es_0() {
                char dvCorrecto;
                int rutDigits;
                dvCorrecto = rut_dv_0.split("-")[1].charAt(0);
                rutDigits = Integer.parseInt(rut_dv_0.split("-")[0]);
                Assertions.assertEquals(dvCorrecto, validarDv(rutDigits));
            }
        }
    }

    @Nested
    class Un_rut_no_es_valido {

        @DisplayName("Si no posee un dígito verificador separado por un guión")
        @ParameterizedTest(name = "Por ejemplo, {0} es un rut no válido.")
        @ValueSource(strings = { "87397881", "44781924", "65539736" })
        void si_no_tiene_digito_verificador(String rutIncompleto) {
            Assertions.assertFalse(validarRut(rutIncompleto));
        }
    }



}
