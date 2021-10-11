/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

public class Utils {

    public static boolean validarRut(String rut) {
        String[] rutParts = rut.split("-");
        int rutDigits;
        char rutDv;
        try {
            rutDigits = Integer.parseInt(rutParts[0]);
            rutDv = rutParts[1].charAt(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e + ": Rut incompleto");
            return false;
        } catch (NumberFormatException e) {
            System.err.println(e + ": Rut sólo debe contener dígitos");
            return false;
        }
        return validarDv(rutDigits) == rutDv;
    }

    // Sugiero cambiar nombre de método a getDv para evitar confusiones en los test
    public static char validarDv(int rutDigits) {
        int accumulator = 0, serie = 2;
        int lastDigit;
        int large = String.valueOf(rutDigits).length();
        for (int i = 0; i < large; i++) {
            if (serie > 7) serie = 2;
            lastDigit = rutDigits % 10;
            accumulator += lastDigit * serie;
            rutDigits = rutDigits / 10;
            serie++;
        }
        int dv = 11 - (accumulator % 11);
        if (dv == 10) {
            return 'K';
        } else if (dv == 11) {
            return '0';
        } else {
            // 0 representa el código 48 en ASCII, la suma representa un valor de 1 al 9
            return (char)(dv + '0');
        }
    }
}
