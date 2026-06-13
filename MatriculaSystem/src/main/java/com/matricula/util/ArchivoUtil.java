package com.matricula.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad para manejo de archivos TXT.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class ArchivoUtil {
    
    private static final String DATA_DIR = "data";
    
    /**
     * Obtiene la ruta completa del archivo.
     * 
     * @param nombreArchivo Nombre del archivo
     * @return Ruta completa del archivo
     */
    public static String getRutaArchivo(String nombreArchivo) {
        return DATA_DIR + File.separator + nombreArchivo;
    }
    
    /**
     * Lee todas las líneas de un archivo TXT.
     * 
     * @param nombreArchivo Nombre del archivo a leer
     * @return Lista de líneas del archivo
     */
    public static List<String> leerLineas(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        String ruta = getRutaArchivo(nombreArchivo);
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Ignorar líneas vacías o comentarios
                if (!linea.trim().isEmpty() && !linea.startsWith("#")) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, retornar lista vacía
            System.out.println("Archivo no encontrado: " + ruta);
        }
        
        return lineas;
    }
    
    /**
     * Escribe una línea al final del archivo.
     * 
     * @param nombreArchivo Nombre del archivo
     * @param linea Línea a escribir
     */
    public static void escribirLinea(String nombreArchivo, String linea) {
        String ruta = getRutaArchivo(nombreArchivo);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en archivo: " + e.getMessage());
        }
    }
    
    /**
     * Sobrescribe todo el contenido del archivo.
     * 
     * @param nombreArchivo Nombre del archivo
     * @param lineas Lista de líneas a escribir
     */
    public static void sobreescribirArchivo(String nombreArchivo, List<String> lineas) {
        String ruta = getRutaArchivo(nombreArchivo);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobreescribir archivo: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si un archivo existe.
     * 
     * @param nombreArchivo Nombre del archivo
     * @return true si el archivo existe
     */
    public static boolean existeArchivo(String nombreArchivo) {
        String ruta = getRutaArchivo(nombreArchivo);
        return new File(ruta).exists();
    }
    
    /**
     * Crea un archivo vacío si no existe.
     * 
     * @param nombreArchivo Nombre del archivo
     */
    public static void crearArchivoSiNoExiste(String nombreArchivo) {
        String ruta = getRutaArchivo(nombreArchivo);
        File archivo = new File(ruta);
        
        if (!archivo.exists()) {
            try {
                // Crear directorio si no existe
                File dir = new File(DATA_DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // Crear archivo vacío
                archivo.createNewFile();
                System.out.println("Archivo creado: " + ruta);
            } catch (IOException e) {
                System.err.println("Error al crear archivo: " + e.getMessage());
            }
        }
    }
    
    /**
     * Cuenta el número de líneas en un archivo.
     * 
     * @param nombreArchivo Nombre del archivo
     * @return Número de líneas
     */
    public static int contarLineas(String nombreArchivo) {
        return leerLineas(nombreArchivo).size();
    }
}
