/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

public class Utils {
    public Utils() {
    }

    //TODO crear logica de verificación de Ruts, para no dejarlo en blanco y pasar el test, cree una validación de DV y
    // un pár de flags, que si todas son verdaderas el rut es valido, tu decides si lo mantienes, lo cree para pasar el
    // test y ya. :)
    public static boolean validarRut(String rut) {
        boolean flagDv = false;
        String[] rutParts = rut.split("-");
        int rutDigits = Integer.parseInt(rutParts[0]);
        System.out.println(rutParts[1]);
        String rutDV = rutParts[1];


        //Verificar DV
        System.out.println(validarDv(rutDigits));
        if(rutDV.equals(validarDv(rutDigits))){
            flagDv = true;
        }

        if(flagDv){
            return true;
        }else {
            return false;
        }
    }

    // TODO: Método para desarrollar en TDD, moudulariza método validadRut() obteniendo el DV (dígito verificador),
    //  validarRut() sólo comprobaría que el DV es el que corresponde retornando true o false.
    public static String validarDv(int rutDigits) {
        int accumulator = 0, serie = 2;
        int lastDigit;
        int large = String.valueOf(rutDigits).length();
        for (int i = 0; i < large; i++) {
            if (serie > 7) serie = 2;
            lastDigit = rutDigits % 10;
            accumulator += lastDigit * serie;
            rutDigits = rutDigits / 10;


            // DEBUGGING
            /**
            System.out.println(
                    "lastDigit: " + lastDigit + "\n"
                            + "accumuluator: " + accumulator + "\n"
                            + "rutDigits: " + rutDigits + "\n"
                            + "serie: " + serie + "\n"
            );
             **/
            serie++;
        }

        // DEBUGGING
        System.out.println("DV: " + (11 - (accumulator % 11)));

        return ""+(11 - (accumulator % 11));
    }
}
