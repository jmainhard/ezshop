/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoboletas.modelo;
import com.mycompany.proyectoboletas.modelo.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


/**
 *
 * @author Esteban
 */
public class ComprobanteTest {
    public static ArrayList<Comprobante> comprobantes = new ArrayList<>();

    public ComprobanteTest() {
    }
    
    @BeforeEach
    public void setUp() {
        Producto p1 = new Producto(1,"Producto 1",5000);
        Producto p2 = new Producto(2,"Producto 2",4000);
        Producto p3 = new Producto(3,"Producto 3",2500);
        Producto p4 = new Producto(4,"Producto 4",7500);

        ArrayList<Producto> productos1 = new ArrayList<>();
        ArrayList<Producto> productos2 = new ArrayList<>();
        productos1.add(p1);
        productos1.add(p2);
        productos1.add(p3);

        productos2.add(p1);
        productos2.add(p4);

        Canasta canasta1 = new Canasta(productos1);
        Canasta canasta2 = new Canasta(productos2);

        Cliente cliente1 = new Cliente("Juan", "1111111-9");
        Cliente cliente2 = new Cliente("Pedro", "1111111-9");
        cliente1.setCanasta(canasta1);
        cliente2.setCanasta(canasta2);

        Comprobante comprobante1 = new Factura(cliente1);
        Comprobante comprobante2 = new Factura(cliente2);
        comprobante1.setNumComprobante(1);
        comprobante1.calcTotal();
        comprobante2.setNumComprobante(2);
        comprobante2.calcTotal();
        comprobantes.add(comprobante1);
        comprobantes.add(comprobante2);
    }
    
    @AfterEach
    public void tearDown() {
    }


    @Test
    @RepeatedTest(5)
    public void TestCalcTotal(){
        assertEquals(comprobantes.get(0).getTotal(),11500);
        assertEquals(comprobantes.get(1).getTotal(),12500);

    }
}
