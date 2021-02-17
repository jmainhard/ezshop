package com.mycompany.proyectoboletas.controlador;

import com.mycompany.proyectoboletas.Imprimible;
import com.mycompany.proyectoboletas.Producto;

/**
 * @author Maximiliano C., Esteban E., Jorge M.
 */
// métodos cargarStock
//         actualizarStock
//         Validador de inventario
public class InventarioController implements Imprimible {

    @Override
    public void imprimir() {
        System.out.println("helo");
    }

    public InventarioController() {
    }

    // TODO
    public Producto addProducto(Producto p) {
        return p;
    }
    
    public Producto delProducto(Producto p) {
        // TODO
        return p;
    }
    
    

    public void mostrarInventario() {
    }
}
