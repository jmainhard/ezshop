package com.mycompany.proyectoboletas.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * Clase genérica para controlar archivos json del proyecto
 * 
 * @param <E> tipo de elementos contenidos en este archivo  
 * @author Jorge M.
 * 
 * Documentación de clases genéricas: 
 *   https://docs.oracle.com/javase/tutorial/java/generics/index.html
 */

public class FileController<E> {
    private TypeToken<Collection<E>> collectionType;

    public FileController(TypeToken<Collection<E>> collectionType) {
        this.collectionType = collectionType;
    }

    public TypeToken<Collection<E>> getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(TypeToken<Collection<E>> collectionType) {
        this.collectionType = collectionType;
    }
    
    /**
     * Guarda una lista de objetos en un archivo con formato JSON
     * 
     * @param lista lista a guardar en formato JSON
     * @param ruta ruta del archivo
     * @return {@code true} si se guarda la lista correctamente {@code false}
     * si la lista no es guardada
     * @throws NullPointerException 
     */
    public boolean saveToJson(List<E> lista, String ruta) throws NullPointerException{
            FileWriter writer;
            
            if (lista == null) {
                throw new NullPointerException("Lista nula o no inicializada\n");
            }
            
            try {
                writer = new FileWriter(ruta);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(lista, collectionType.getType(), writer);
                writer.close();
                return true;
            } catch (IOException ex) {
                System.err.println("Error al crear el archivo");
            } catch (JsonIOException ex) {
                System.err.println("Error al guardar lista (Json)");
            } catch (Exception e) {
                System.err.println("Error "+ e);
            }
            
            return false;
    }

    /**
     * Carga los objetos de un archivo JSON dado
     * 
     * @param ruta ruta del archivo
     * @return {@code ArrayList<E>} con los objetos del archivo JSON
     * // FIXME cuando el JSON está completamente vacío retorna nulo
     */
    public ArrayList<E> loadFromJson(String ruta) {
            ArrayList<E> lista = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(ruta));
                Gson gson = new Gson();
                lista = gson.fromJson(br, collectionType.getType());
            } catch (IOException ex) {
                System.err.println("Error "+ ex);
            } catch (JsonSyntaxException ex) {
                System.err.println("Error al intentar cargar Json "+ ex);
            }
            return lista;
    }

}
