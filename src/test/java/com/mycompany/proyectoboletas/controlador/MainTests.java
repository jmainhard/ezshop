package com.mycompany.proyectoboletas.controlador;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Disabled
public class MainTests {
    public static Scanner teclado = new Scanner(System.in);
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    static String salidaEsperadaInicial = "\nIngrese el número de comprobante\n" +
            "Boleta encontrado!\n" +
            "\n" +
            "              -- COMPROBANTE --\n" +
            "---------------------------------------------\n" +
            "\n" +
            "Tipo: Boleta\n" +
            "Num comprobante: 1\n" +
            "Fecha: 4 de diciembre de 2021 16:52:41\n" +
            "Nombre cliente: admin\n" +
            "Rut cliente: 11111111-1\n" +
            "Productos: (Nombre / Precio / ID)\n" +
            "-> Taladro Bauker / $19990.0 / 4\n" +
            "Total compra: $19990.0 IVA inc.\n" +
            "---------------------------------------------\n";
    static String otraSalida = "Ingrese el número de comprobante\r\n" +
            "Boleta no encontrado.";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getComprobanteTest(){
        int tipo = 2;

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.contabilidad.setComprobantesTotales("jsons/historialComprobantesTest.json");
        Main.getComprobante(tipo);

        String salidaObtenida = outputStreamCaptor.toString().trim().replaceAll("[^\\p{L}\\p{N}]", "").toLowerCase();
        String salidaEsperada = salidaEsperadaInicial.trim().replaceAll("[^\\p{L}\\p{N}]", "").toLowerCase();

        Assertions.assertEquals(salidaEsperada, salidaObtenida);


    }
}
