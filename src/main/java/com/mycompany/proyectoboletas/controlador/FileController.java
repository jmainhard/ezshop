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
 * @see TypeToken
 * @author Jorge M.
 *
 * Documentación de clases genéricas:
 *  https://docs.oracle.com/javase/tutorial/java/generics/index.html
 */
public class FileController<E> {
    private TypeToken<Collection<E>> collectionType;

    /**
     * Inicializa un nuevo objeto {@code FileController}, maneja una colección de tipo {@code <E>}
     * @param collectionType tipo de colección a ser controlada por esta instancia
     */
    public FileController(TypeToken<Collection<E>> collectionType) {
        this.collectionType = collectionType;
    }

    /**
     * Obtiene tipo de colección de esta instancia
     * @return {@code TypeToken<Collection<E>>} de esta lista
     */
    public TypeToken<Collection<E>> getCollectionType() {
        return collectionType;
    }

    /**
     * Establece tipo de colección de esta instancia
     * @param collectionType {@code TypeToken<Collection<E>>}
     */
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
     * @throws NullPointerException si la lista no ha sido inicializada
     */
    public boolean saveToJson(List<E> lista, String ruta) throws NullPointerException {
            if (lista == null) {
                throw new NullPointerException("Lista nula o no inicializada\n");
            }
            try (FileWriter writer = new FileWriter(ruta)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(lista, collectionType.getType(), writer);
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
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
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
