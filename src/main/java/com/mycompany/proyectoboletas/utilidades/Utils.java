/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

public class Utils {
    public static boolean validarRut(String rut) {
        // TODO pasar lógica a getDv()
        String[] rutParts = rut.split("-");
        int rutDigits = Integer.valueOf(rutParts[0]);
        int rutDV     = Integer.valueOf(rutParts[1]);
        int accumulator = 0, serie = 2;
        int lastDigit;

        for (int i = 0; i < rutParts[0].length(); i++) {
            if (serie > 7) serie = 2;
            lastDigit = rutDigits % 10;
            accumulator += lastDigit * serie;
            rutDigits = rutDigits / 10;
            serie++;

            // DEBUGGING
            System.out.println(
                    "lastDigit: " + lastDigit + "\n"
                    + "accumuluator: " + accumulator + "\n"
                    + "rutDigits: " + rutDigits + "\n"
                    + "serie: " + serie + "\n"
            );
        }

        // DEBUGGING
        System.out.println("DV: " + (11 - accumulator % 11));

        return false;
    }

    // TODO: Método para desarrollar en TDD, moudulariza método validadRut() obteniendo el DV (dígito verificador),
    //  validarRut() sólo comprobaría que el DV es el que corresponde retornando true o false.
    // public static char getDv() {}
}
