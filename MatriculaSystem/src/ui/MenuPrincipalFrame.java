package ui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {
    private JButton btnMantenimiento, btnRegistro, btnConsulta, btnReportes, btnSalir;

    public MenuPrincipalFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Matrícula - Menú Principal");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("SISTEMA DE MATRÍCULA ACADÉMICA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(200, 30, 400, 40);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Menú Principal");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setBounds(320, 70, 200, 30);
        add(lblSubtitulo);

        // Panel de botones principales
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 30, 30));
        panelBotones.setBounds(150, 150, 500, 300);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnMantenimiento = new JButton("MANTENIMIENTO");
        btnMantenimiento.setFont(new Font("Arial", Font.BOLD, 14));
        btnMantenimiento.setPreferredSize(new Dimension(200, 100));
        btnMantenimiento.addActionListener(e -> abrirMantenimiento());
        panelBotones.add(btnMantenimiento);

        btnRegistro = new JButton("REGISTRO");
        btnRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistro.setPreferredSize(new Dimension(200, 100));
        btnRegistro.addActionListener(e -> abrirRegistro());
        panelBotones.add(btnRegistro);

        btnConsulta = new JButton("CONSULTA");
        btnConsulta.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsulta.setPreferredSize(new Dimension(200, 100));
        btnConsulta.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Consulta en desarrollo"));
        panelBotones.add(btnConsulta);

        btnReportes = new JButton("REPORTES");
        btnReportes.setFont(new Font("Arial", Font.BOLD, 14));
        btnReportes.setPreferredSize(new Dimension(200, 100));
        btnReportes.addActionListener(e -> abrirReportes());
        panelBotones.add(btnReportes);

        add(panelBotones);

        btnSalir = new JButton("SALIR");
        btnSalir.setBounds(350, 500, 100, 35);
        btnSalir.addActionListener(e -> salir());
        add(btnSalir);
    }

    private void abrirMantenimiento() {
        JMenu menu = new JMenu("Mantenimiento");
        JMenuItem itemAlumnos = new JMenuItem("Alumnos");
        JMenuItem itemCarreras = new JMenuItem("Carreras");
        JMenuItem itemCursos = new JMenuItem("Cursos");

        itemAlumnos.addActionListener(e -> {
            MantenimientoAlumnosFrame frame = new MantenimientoAlumnosFrame();
            frame.setVisible(true);
        });

        itemCarreras.addActionListener(e -> {
            MantenimientoCarrerasFrame frame = new MantenimientoCarrerasFrame();
            frame.setVisible(true);
        });

        itemCursos.addActionListener(e -> {
            MantenimientoCursosFrame frame = new MantenimientoCursosFrame();
            frame.setVisible(true);
        });

        JPopupMenu popup = new JPopupMenu();
        popup.add(itemAlumnos);
        popup.add(itemCarreras);
        popup.add(itemCursos);
        popup.show(btnMantenimiento, 0, btnMantenimiento.getHeight());
    }

    private void abrirRegistro() {
        JMenu menu = new JMenu("Registro");
        JMenuItem itemMatricula = new JMenuItem("Matrícula");
        JMenuItem itemRetiro = new JMenuItem("Retiro");

        itemMatricula.addActionListener(e -> {
            MatriculaFrame frame = new MatriculaFrame();
            frame.setVisible(true);
        });

        itemRetiro.addActionListener(e -> {
            RetiroFrame frame = new RetiroFrame();
            frame.setVisible(true);
        });

        JPopupMenu popup = new JPopupMenu();
        popup.add(itemMatricula);
        popup.add(itemRetiro);
        popup.show(btnRegistro, 0, btnRegistro.getHeight());
    }

    private void abrirReportes() {
        ReportesFrame frame = new ReportesFrame();
        frame.setVisible(true);
    }

    private void salir() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Salir del sistema?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
