package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.modelo.Cliente;
import com.mycompany.proyectoboletas.modelo.Producto;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jorge M.
 * FIXME: Test no debiera modificar archivos
 */
public class ListControllerTest {
    static ListController<Cliente> listHandler;
    static ArrayList<Cliente> clientList;
    String jsonText;

    Cliente cliente_1;
    Cliente cliente_2;
    Producto producto_1;
    Producto producto_2;
    Producto producto_3;

    @BeforeAll
    static void beforeAll() {
        listHandler  = new ListController("clientesTest.json", new TypeToken<Collection<Cliente>>(){});
    }

    @BeforeEach
    public void setUp() {
        clientList = new ArrayList<>();
        cliente_1 = new Cliente("Cliente Default 1", "111111-1");
        cliente_2= new Cliente("Cliente Default 2", "222222-2");
        producto_1 = new Producto(1, "Producto Default 1", 19990);
        producto_2 = new Producto(1, "Producto Default 2", 37000);
        producto_3 = new Producto(1, "Producto Default 3", 450000);

        cliente_1.getCanasta().addProducto(producto_1);
        cliente_1.getCanasta().addProducto(producto_2);
        cliente_1.getCanasta().addProducto(producto_3);
        
        cliente_2.getCanasta().addProducto(producto_1);
        cliente_2.getCanasta().addProducto(producto_2);
        cliente_2.getCanasta().addProducto(producto_3);
        
        clientList.add(cliente_1);
        clientList.add(cliente_2);
        clientList.add(cliente_2);
    }
    
    @AfterEach
    public void tearDown() {
        cliente_1 = null;
        cliente_2 = null;
        producto_1 = null;
        producto_2 = null;
        producto_3 = null;
        clientList.clear();
    }

    @Test
    public void testGuardarObjetos() {
        assertTrue(listHandler.guardarObjetos(clientList));
    }

    @Test
    public void testCargarObjetos() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(clientList);
        
        ArrayList esperado = gson.fromJson(jsonString, new TypeToken<Collection<Cliente>>(){}.getType());
        ArrayList obtenido = listHandler.cargarObjetos();
        
        assertEquals(esperado.size(), obtenido.size());
    }
    
}
