package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.Cliente;
import com.mycompany.proyectoboletas.Producto;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jorge M.
 */
public class ListControllerTest {
    ListController<Cliente> listHandler = new ListController("clientesTest.json", new TypeToken<Collection<Cliente>>(){});
    ArrayList<Cliente> objetosCliente = new ArrayList<>();
    String jsonText;
    
    public ListControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Cliente cliente1 = new Cliente("Cliente Default 1", "111111-1");
        Cliente cliente2 = new Cliente("Cliente Default 2", "222222-2");
        Producto producto1 = new Producto(1, "Producto Default 1", 19990);
        Producto producto2 = new Producto(1, "Producto Default 2", 37000);
        Producto producto3 = new Producto(1, "Producto Default 3", 450000);
        
        
        cliente1.getCanasta().addProducto(producto1);
        cliente1.getCanasta().addProducto(producto2);
        cliente1.getCanasta().addProducto(producto3);
        
        cliente2.getCanasta().addProducto(producto1);
        cliente2.getCanasta().addProducto(producto2);
        cliente2.getCanasta().addProducto(producto3);
        
        objetosCliente.add(cliente1);
        objetosCliente.add(cliente2);
        objetosCliente.add(cliente2);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test de método guardarObjetos, clase ListController.
     */
    @Test
    public void testGuardarObjetos() {
        System.out.println("guardarObjetos");
        assertTrue(listHandler.guardarObjetos(objetosCliente));
    }

    /**
     * Test de método cargarObjetos, clase ListController.
     */
    @Test
    public void testCargarObjetos() {
        System.out.println("cargarObjetos");
        Gson gson = new Gson();
        String jsonString = gson.toJson(objetosCliente);
        
        ArrayList esperado = gson.fromJson(jsonString, new TypeToken<Collection<Cliente>>(){}.getType());
        ArrayList obtenido = listHandler.cargarObjetos();
        
        assertEquals(esperado.size(), obtenido.size());
    }
    
}
