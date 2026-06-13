package com.matricula.ui;

import com.matricula.dao.AlumnoDAO;
import com.matricula.dao.CursoDAO;
import com.matricula.model.Alumno;
import com.matricula.model.Curso;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

/**
 * Ventana del Módulo de Mantenimiento (Alumnos y Cursos).
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class MantenimientoFrame extends JFrame {
    
    private MenuPrincipalFrame menuPrincipal;
    private JTabbedPane tabbedPane;
    
    // DAOs
    private AlumnoDAO alumnoDAO;
    private CursoDAO cursoDAO;
    
    /**
     * Constructor del módulo de mantenimiento.
     * 
     * @param menuPrincipal Referencia al menú principal
     */
    public MantenimientoFrame(MenuPrincipalFrame menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.alumnoDAO = new AlumnoDAO();
        this.cursoDAO = new CursoDAO();
        
        initComponents();
        configurarVentana();
    }
    
    /**
     * Configura las propiedades de la ventana.
     */
    private void configurarVentana() {
        setTitle("Mantenimiento - Alumnos y Cursos");
        setSize(900, 650);
        setLocationRelativeTo(menuPrincipal); // Centrar respecto al menú principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Inicializa los componentes de la interfaz.
     */
    private void initComponents() {
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        // Título
        JLabel lblTitulo = new JLabel("MÓDULO DE MANTENIMIENTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setPreferredSize(new Dimension(900, 50));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        // Tabbed Pane para las pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Pestaña Alumnos
        JPanel panelAlumnos = crearPanelAlumnos();
        tabbedPane.addTab("Alumnos", null, panelAlumnos, "Gestión de Alumnos");
        
        // Pestaña Cursos
        JPanel panelCursos = crearPanelCursos();
        tabbedPane.addTab("Cursos", null, panelCursos, "Gestión de Cursos");
        
        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);
        
        // Botón Cerrar en la parte inferior
        JPanel panelBotones = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(100, 35));
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelBotones.add(btnCerrar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    /**
     * Crea el panel para gestión de alumnos.
     * 
     * @return Panel de alumnos
     */
    private JPanel crearPanelAlumnos() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(880, 550));
        
        // Título de la sección
        JLabel lblSeccion = new JLabel("REGISTRO DE ALUMNOS");
        lblSeccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeccion.setBounds(20, 10, 300, 30);
        panel.add(lblSeccion);
        
        // Campo Código (solo lectura)
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 50, 80, 25);
        panel.add(lblCodigo);
        
        JTextField txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBackground(java.awt.Color.LIGHT_GRAY);
        txtCodigo.setBounds(100, 50, 150, 25);
        txtCodigo.setText(alumnoDAO.obtenerNuevoCodigo());
        panel.add(txtCodigo);
        
        // Campo DNI
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(270, 50, 80, 25);
        panel.add(lblDni);
        
        JTextField txtDni = new JTextField();
        txtDni.setBounds(350, 50, 120, 25);
        panel.add(txtDni);
        
        // Campo Nombres
        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(20, 90, 80, 25);
        panel.add(lblNombres);
        
        JTextField txtNombres = new JTextField();
        txtNombres.setBounds(100, 90, 370, 25);
        panel.add(txtNombres);
        
        // Campo Apellidos
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 130, 80, 25);
        panel.add(lblApellidos);
        
        JTextField txtApellidos = new JTextField();
        txtApellidos.setBounds(100, 130, 370, 25);
        panel.add(txtApellidos);
        
        // Campo Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 170, 80, 25);
        panel.add(lblEmail);
        
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(100, 170, 370, 25);
        panel.add(txtEmail);
        
        // Campo Teléfono
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 210, 80, 25);
        panel.add(lblTelefono);
        
        JTextField txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 210, 150, 25);
        panel.add(txtTelefono);
        
        // Campo Dirección
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 250, 80, 25);
        panel.add(lblDireccion);
        
        JTextField txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 250, 370, 25);
        panel.add(txtDireccion);
        
        // Botón Guardar
        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuardar.setBounds(20, 300, 150, 35);
        panel.add(btnGuardar);
        
        // Botón Limpiar
        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(180, 300, 120, 35);
        panel.add(btnLimpiar);
        
        // Botón Buscar por DNI
        JButton btnBuscar = new JButton("BUSCAR POR DNI");
        btnBuscar.setBounds(310, 300, 160, 35);
        panel.add(btnBuscar);
        
        // Listener para guardar alumno
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAlumno(txtCodigo, txtDni, txtNombres, txtApellidos, 
                             txtEmail, txtTelefono, txtDireccion);
            }
        });
        
        // Listener para limpiar formulario
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormularioAlumno(txtCodigo, txtDni, txtNombres, txtApellidos,
                                       txtEmail, txtTelefono, txtDireccion);
            }
        });
        
        // Listener para buscar por DNI
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = txtDni.getText().trim();
                if (dni.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(MantenimientoFrame.this,
                        "Ingrese un DNI para buscar", "Búsqueda", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                List<Alumno> encontrados = alumnoDAO.buscarPorDni(dni);
                if (encontrados.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(MantenimientoFrame.this,
                        "No se encontró alumno con DNI: " + dni, "Resultado", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder("Alumnos encontrados:\n\n");
                    for (Alumno a : encontrados) {
                        sb.append("Código: ").append(a.getCodigo()).append("\n");
                        sb.append("Nombre: ").append(a.getNombreCompleto()).append("\n");
                        sb.append("Email: ").append(a.getEmail()).append("\n");
                        sb.append("---------------------------\n");
                    }
                    javax.swing.JOptionPane.showMessageDialog(MantenimientoFrame.this,
                        sb.toString(), "Resultado", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        // Label informativo
        JLabel lblInfo = new JLabel("Total de alumnos registrados: " + alumnoDAO.contar());
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setBounds(20, 350, 400, 25);
        panel.add(lblInfo);
        
        return panel;
    }
    
    /**
     * Crea el panel para gestión de cursos.
     * 
     * @return Panel de cursos
     */
    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(880, 550));
        
        // Título de la sección
        JLabel lblSeccion = new JLabel("REGISTRO DE CURSOS");
        lblSeccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeccion.setBounds(20, 10, 300, 30);
        panel.add(lblSeccion);
        
        // Mensaje de información
        JLabel lblInfo = new JLabel("Funcionalidad de cursos en desarrollo...");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInfo.setForeground(java.awt.Color.GRAY);
        lblInfo.setBounds(20, 60, 400, 30);
        panel.add(lblInfo);
        
        JLabel lblInfo2 = new JLabel("Total de cursos registrados: " + cursoDAO.contar());
        lblInfo2.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo2.setBounds(20, 100, 400, 25);
        panel.add(lblInfo2);
        
        return panel;
    }
    
    /**
     * Guarda un nuevo alumno.
     */
    private void guardarAlumno(JTextField txtCodigo, JTextField txtDni, JTextField txtNombres,
                               JTextField txtApellidos, JTextField txtEmail,
                               JTextField txtTelefono, JTextField txtDireccion) {
        // Validaciones básicas
        if (txtDni.getText().trim().isEmpty() || 
            txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Complete los campos obligatorios (DNI, Nombres, Apellidos)",
                "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Crear objeto Alumno
        Alumno alumno = new Alumno();
        alumno.setCodigo(txtCodigo.getText().trim());
        alumno.setDni(txtDni.getText().trim());
        alumno.setNombres(txtNombres.getText().trim());
        alumno.setApellidos(txtApellidos.getText().trim());
        alumno.setEmail(txtEmail.getText().trim());
        alumno.setTelefono(txtTelefono.getText().trim());
        alumno.setDireccion(txtDireccion.getText().trim());
        
        // Guardar en archivo
        boolean exito = alumnoDAO.guardar(alumno);
        
        if (exito) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Alumno registrado exitosamente\nCódigo: " + alumno.getCodigo(),
                "Registro Exitoso",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Generar nuevo código y limpiar
            txtCodigo.setText(alumnoDAO.obtenerNuevoCodigo());
            limpiarFormularioAlumno(txtCodigo, txtDni, txtNombres, txtApellidos,
                                   txtEmail, txtTelefono, txtDireccion);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error al registrar el alumno.\nEl código ya existe.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Limpia el formulario de alumno.
     */
    private void limpiarFormularioAlumno(JTextField txtCodigo, JTextField txtDni, 
                                         JTextField txtNombres, JTextField txtApellidos,
                                         JTextField txtEmail, JTextField txtTelefono,
                                         JTextField txtDireccion) {
        txtDni.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtDni.requestFocus();
    }
}
