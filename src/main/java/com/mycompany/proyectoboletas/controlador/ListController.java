package com.mycompany.proyectoboletas.controlador;

import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Clase genérica para guardar y cargar 
 * listas de objetos a/desde un archivo json dado
 * 
 * @param <E> tipo de elementos en esta lista
 * @author Jorge M.
 * 
 * @see FileController
 */
public class ListController<E> {
    private final TypeToken<Collection<E>> collectionType; // se usa para parsear en runtime y no perder el tipo de origen
    private final String archivoJson;
    private static final String RUTA = "jsons/";

    /**
     * @param archivoJson archivo json con los objetos de esta lista
     * @param collectionType {@code TypeToken} del tipo de colección de esta lista
     */    
    public ListController(String archivoJson, TypeToken<Collection<E>> collectionType) {
        this.archivoJson = archivoJson;
        this.collectionType = collectionType;
    }
    
    /**
     * Guarda una lista de objetos en formato JSON
     * @param listaObjetos lista que se desea guardar al archivo json
     *  {@code archivoJson} de este ListController
     */
    public boolean guardarObjetos(ArrayList<E> listaObjetos) {
        FileController<E> jsonHandler = new FileController<>(collectionType);
         try {
            if ( jsonHandler.saveToJson(listaObjetos, RUTA+archivoJson) ) {
//                System.out.println("\nLista guardada exitosamente\n");
                return true;
            }
        } catch (NullPointerException ex) {
            System.err.println("Error al guardar: "+ ex);
        } catch (Exception ex) {
            System.err.println("Error al intentar guardar esta lista "+ ex);
        }
        return false;
    }
    
    /**
     * Carga una lista de objetos desde JSON
     * @return lista de objetos del tipo {@literal <E>}
     */
    public ArrayList<E> cargarObjetos() {
        FileController<E> jsonHandler = new FileController<>(collectionType);
        return jsonHandler.loadFromJson(RUTA+archivoJson);
    }
}
