package com.matricula.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Menú Principal del sistema.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class MenuPrincipalFrame extends JFrame {
    
    private JButton btnMantenimiento;
    private JButton btnRegistro;
    private JButton btnConsulta;
    private JButton btnReporte;
    private JButton btnSalir;
    
    /**
     * Constructor del menú principal.
     */
    public MenuPrincipalFrame() {
        initComponents();
        configurarVentana();
    }
    
    /**
     * Configura las propiedades de la ventana.
     */
    private void configurarVentana() {
        setTitle("Sistema de Matrícula - Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Inicializa los componentes de la interfaz.
     */
    private void initComponents() {
        // Crear barra de menú
        JMenuBar barraMenu = new JMenuBar();
        
        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirSistema();
            }
        });
        menuArchivo.add(menuItemSalir);
        barraMenu.add(menuArchivo);
        
        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de...");
        menuItemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAcercaDe();
            }
        });
        menuAyuda.add(menuItemAcercaDe);
        barraMenu.add(menuAyuda);
        
        setJMenuBar(barraMenu);
        
        // Panel principal con layout absoluto
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setPreferredSize(new Dimension(800, 600));
        
        // Título
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE MATRÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 30, 800, 40);
        panelPrincipal.add(lblTitulo);
        
        // Subtítulo
        JLabel lblSubtitulo = new JLabel("Seleccione una opción del menú");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(0, 70, 800, 30);
        panelPrincipal.add(lblSubtitulo);
        
        // Botón Mantenimiento (grande)
        btnMantenimiento = new JButton("MANTENIMIENTO");
        btnMantenimiento.setFont(new Font("Arial", Font.BOLD, 16));
        btnMantenimiento.setBounds(150, 150, 220, 80);
        panelPrincipal.add(btnMantenimiento);
        
        // Botón Registro
        btnRegistro = new JButton("REGISTRO");
        btnRegistro.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistro.setBounds(430, 150, 220, 80);
        panelPrincipal.add(btnRegistro);
        
        // Botón Consulta
        btnConsulta = new JButton("CONSULTA");
        btnConsulta.setFont(new Font("Arial", Font.BOLD, 16));
        btnConsulta.setBounds(150, 280, 220, 80);
        panelPrincipal.add(btnConsulta);
        
        // Botón Reporte
        btnReporte = new JButton("REPORTE");
        btnReporte.setFont(new Font("Arial", Font.BOLD, 16));
        btnReporte.setBounds(430, 280, 220, 80);
        panelPrincipal.add(btnReporte);
        
        // Botón Salir
        btnSalir = new JButton("SALIR DEL SISTEMA");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBounds(300, 420, 200, 40);
        panelPrincipal.add(btnSalir);
        
        // Descripción de módulos
        JLabel lblDescMantenimiento = new JLabel("<html><center>Alumnos y Cursos</center></html>");
        lblDescMantenimiento.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescMantenimiento.setBounds(150, 235, 220, 30);
        panelPrincipal.add(lblDescMantenimiento);
        
        JLabel lblDescRegistro = new JLabel("<html><center>Matrículas y Retiros</center></html>");
        lblDescRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescRegistro.setBounds(430, 235, 220, 30);
        panelPrincipal.add(lblDescRegistro);
        
        JLabel lblDescConsulta = new JLabel("<html><center>Buscar información</center></html>");
        lblDescConsulta.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescConsulta.setBounds(150, 365, 220, 30);
        panelPrincipal.add(lblDescConsulta);
        
        JLabel lblDescReporte = new JLabel("<html><center>Generar reportes</center></html>");
        lblDescReporte.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescReporte.setBounds(430, 365, 220, 30);
        panelPrincipal.add(lblDescReporte);
        
        // Agregar listeners a botones
        btnMantenimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirModuloMantenimiento();
            }
        });
        
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Módulo de Registro\nEn desarrollo...",
                    "Próximamente",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Módulo de Consulta\nEn desarrollo...",
                    "Próximamente",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Módulo de Reporte\nEn desarrollo...",
                    "Próximamente",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirSistema();
            }
        });
        
        // Agregar panel a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    /**
     * Abre el módulo de mantenimiento.
     */
    private void abrirModuloMantenimiento() {
        MantenimientoFrame mantenimiento = new MantenimientoFrame(this);
        mantenimiento.setVisible(true);
    }
    
    /**
     * Muestra el diálogo Acerca de.
     */
    private void mostrarAcercaDe() {
        String mensaje = "SISTEMA DE GESTIÓN DE MATRÍCULA\n\n" +
                        "Versión: 1.0\n" +
                        "Desarrollado para:\n" +
                        "Curso de Algoritmos y Estructura de Datos\n\n" +
                        "Tecnologías:\n" +
                        "- Java Swing\n" +
                        "- Archivos TXT\n\n" +
                        "© 2026 - Todos los derechos reservados";
        
        JOptionPane.showMessageDialog(this, mensaje, "Acerca de", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Sale del sistema.
     */
    private void salirSistema() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir del sistema?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
