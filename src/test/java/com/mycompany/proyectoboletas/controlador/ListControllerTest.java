package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyectoboletas.modelo.Cliente;
import com.mycompany.proyectoboletas.modelo.HistorialCliente;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jorge M.
 */
class ListControllerTest {
    static JsonController<HistorialCliente> listHandler;
    static ArrayList<HistorialCliente> clientList;
    String jsonText;

    Cliente cliente_1;
    Cliente cliente_2;
    HistorialCliente histCliente_1;
    HistorialCliente histCliente_2;

    @BeforeAll
    static void beforeAll() {
        listHandler  = new JsonController("clientesTest.json", new TypeToken<Collection<HistorialCliente>>(){});
    }

    @BeforeEach
    void setUp() {
        clientList = new ArrayList<>();
        cliente_1 = new Cliente("Test cliente 1", "11111111-1");
        cliente_2 = new Cliente("Test cliente 2", "22222222-2");
        histCliente_1 = new HistorialCliente(cliente_1);
        histCliente_2 = new HistorialCliente(cliente_2);

        clientList.add(histCliente_1);
        clientList.add(histCliente_2);
    }
    
    @AfterEach
    void tearDown() {
        cliente_1 = null;
        cliente_2 = null;
        clientList.clear();
    }

    @Test
    void testGuardarObjetos() {
        assertTrue(listHandler.guardarObjetos(clientList));
    }

    @Test
    void testCargarObjetos() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(clientList);

        ArrayList esperado = gson.fromJson(jsonString, new TypeToken<Collection<HistorialCliente>>(){}.getType());
        ArrayList obtenido = listHandler.cargarObjetos();
        
        assertEquals(esperado.size(), obtenido.size());
    }
    
}
