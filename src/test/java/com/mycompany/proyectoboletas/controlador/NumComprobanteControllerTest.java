package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.modelo.Boleta;
import com.mycompany.proyectoboletas.modelo.Cliente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para {@link NumComprobanteController}
 */
class NumComprobanteControllerTest {
    static NumComprobanteController numComprobantes;
    static Boleta boletaTest;
    static int cantComprobantesGenerados;

    @BeforeAll
    static void beforeAll() {
        boletaTest = new Boleta(new Cliente("TEST-CLIENT", "11111111-1"));
        numComprobantes = new NumComprobanteController();
        numComprobantes.setComprobantes();
        cantComprobantesGenerados = numComprobantes.getComprobantes();
    }

    @Test
    void testGenerarNumComprobante() {
        int cantComprobantesEsperada, cantComprobantesNueva;
        cantComprobantesEsperada = ++cantComprobantesGenerados;
        numComprobantes.generarNumComprobante(boletaTest);
        cantComprobantesNueva = numComprobantes.getComprobantes();
        assertEquals(cantComprobantesEsperada, cantComprobantesNueva);
    }

    @Test
    void testActualizarNumero() {
        int cantComprobantesEsperada, cantComprobantesObtenida;
        cantComprobantesEsperada = 0;
        numComprobantes.setComprobantes(0);
        numComprobantes.actualizarNumero();
        cantComprobantesObtenida = NumComprobanteController
                .getNumComprobanteHandler()
                .cargarObjetos()
                .get(0)
                .getComprobantes();
        assertEquals(cantComprobantesEsperada, cantComprobantesObtenida);
    }
}