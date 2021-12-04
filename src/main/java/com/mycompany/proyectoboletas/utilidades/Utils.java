/*
 *  @author Esteban E., Jorge M.
 *  08-10-2021
 */

package com.mycompany.proyectoboletas.utilidades;

import com.mycompany.proyectoboletas.controlador.Main;

import java.util.InputMismatchException;

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

    public static String askNombre() {
        System.out.println("\n   < Ingrese Nombre >");
        return Main.teclado.nextLine();
    }

    public static String askRut() {
        boolean valido = false;
        String rut = "NO-DEFINIDO";
        while (!valido) {
            System.out.println("   < Ingrese Rut, sin puntos y con guión (ej. 95797534-8) >");
            rut = Main.teclado.next();
            if (validarRut(rut)) {
                valido = true;
            } else {
                System.err.println("Error: Rut no válido, intente nuevamente\n");
            }
        }
        Main.teclado.nextLine(); // Flush scanner
        return rut;
    }

    public static int askIdProducto() {
        int id = -1;
        int inventarioSize = Main.inventarioController.getInventario().size();

        while (id < 1 || id > inventarioSize) {
            id = -1;
            System.out.println("   < Ingrese ID >");
            try {
                id = Main.teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Error: Ingrese un número "+ e);
                Main.teclado.next();
            } catch (Exception e) {
                System.err.println("Error: "+ e);
                Main.teclado.next();
            }
        }
        System.out.println("\nSeleccionado: "+ id);

        return id;
    }
}
