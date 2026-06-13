package com.matricula.ui;

import com.matricula.dao.AlumnoDAO;
import com.matricula.dao.CursoDAO;
import com.matricula.dao.MatriculaDAO;
import com.matricula.model.Alumno;
import com.matricula.model.Curso;
import com.matricula.model.Matricula;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Ventana de Login del sistema.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class LoginFrame extends JFrame {
    
    private JTextField txtUsuario;
    private JTextField txtPassword;
    private JButton btnIngresar;
    private JButton btnSalir;
    
    // Credenciales hardcodeadas
    private static final String USUARIO_VALIDO = "admin";
    private static final String PASSWORD_VALIDO = "admin";
    
    /**
     * Constructor de la ventana de login.
     */
    public LoginFrame() {
        initComponents();
        configurarVentana();
    }
    
    /**
     * Configura las propiedades de la ventana.
     */
    private void configurarVentana() {
        setTitle("Sistema de Matrícula - Login");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Inicializa los componentes de la interfaz.
     */
    private void initComponents() {
        // Panel principal con layout absoluto
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setPreferredSize(new Dimension(400, 300));
        
        // Título
        JLabel lblTitulo = new JLabel("SISTEMA DE MATRÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(20, 20, 340, 30);
        panelPrincipal.add(lblTitulo);
        
        // Subtítulo
        JLabel lblSubtitulo = new JLabel("Ingrese sus credenciales");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(20, 50, 340, 20);
        panelPrincipal.add(lblSubtitulo);
        
        // Label Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        lblUsuario.setBounds(50, 90, 80, 25);
        panelPrincipal.add(lblUsuario);
        
        // TextField Usuario
        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 90, 200, 25);
        panelPrincipal.add(txtUsuario);
        
        // Label Password
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPassword.setBounds(50, 130, 80, 25);
        panelPrincipal.add(lblPassword);
        
        // TextField Password
        txtPassword = new JTextField();
        txtPassword.setBounds(140, 130, 200, 25);
        panelPrincipal.add(txtPassword);
        
        // Botón Ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(100, 180, 120, 30);
        panelPrincipal.add(btnIngresar);
        
        // Botón Salir
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(240, 180, 100, 30);
        panelPrincipal.add(btnSalir);
        
        // Mensaje de ayuda
        JLabel lblAyuda = new JLabel("Use: admin / admin");
        lblAyuda.setFont(new Font("Arial", Font.ITALIC, 10));
        lblAyuda.setForeground(java.awt.Color.GRAY);
        lblAyuda.setHorizontalAlignment(SwingConstants.CENTER);
        lblAyuda.setBounds(20, 230, 340, 20);
        panelPrincipal.add(lblAyuda);
        
        // Agregar listeners
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Agregar panel a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    /**
     * Valida las credenciales del usuario.
     */
    private void validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();
        
        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese usuario y contraseña",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (usuario.equals(USUARIO_VALIDO) && password.equals(PASSWORD_VALIDO)) {
            JOptionPane.showMessageDialog(this,
                "¡Bienvenido al Sistema de Matrícula!",
                "Login Exitoso",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir menú principal
            MenuPrincipalFrame menuPrincipal = new MenuPrincipalFrame();
            menuPrincipal.setVisible(true);
            this.dispose(); // Cerrar ventana de login
        } else {
            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos",
                "Error de Autenticación",
                JOptionPane.ERROR_MESSAGE);
            
            // Limpiar campos
            txtUsuario.setText("");
            txtPassword.setText("");
            txtUsuario.requestFocus();
        }
    }
}
