package ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnSalir;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Matrícula - Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel lblTitulo = new JLabel("SISTEMA DE MATRÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(100, 30, 200, 30);
        add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 80, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 80, 200, 25);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 120, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 120, 200, 25);
        add(txtPassword);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(80, 170, 100, 35);
        add(btnIngresar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 170, 100, 35);
        add(btnSalir);

        btnIngresar.addActionListener(e -> validarLogin());
        btnSalir.addActionListener(e -> System.exit(0));
        
        // Permitir Enter para login
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    validarLogin();
                }
            }
        });
    }

    private void validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (usuario.equals("admin") && password.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Bienvenido al Sistema");
            dispose();
            MenuPrincipalFrame menu = new MenuPrincipalFrame();
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            txtUsuario.setText("");
            txtPassword.setText("");
            txtUsuario.requestFocus();
        }
    }
}
