package com.matricula.main;

import com.matricula.ui.LoginFrame;
import com.matricula.util.ArchivoUtil;

/**
 * Clase principal de inicio del sistema.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal de entrada.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Inicializar archivos de datos
        inicializarArchivos();
        
        // Configurar Look and Feel del sistema
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo configurar el Look and Feel: " + e.getMessage());
        }
        
        // Mostrar ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }
        });
    }
    
    /**
     * Inicializa los archivos de datos si no existen.
     */
    private static void inicializarArchivos() {
        ArchivoUtil.crearArchivoSiNoExiste("alumnos.txt");
        ArchivoUtil.crearArchivoSiNoExiste("cursos.txt");
        ArchivoUtil.crearArchivoSiNoExiste("matriculas.txt");
        ArchivoUtil.crearArchivoSiNoExiste("retiros.txt");
        
        System.out.println("Archivos de datos inicializados correctamente.");
    }
}
