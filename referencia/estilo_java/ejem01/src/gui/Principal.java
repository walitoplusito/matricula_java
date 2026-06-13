package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import examen.Lavadora;

public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitulo;
    private JTextArea txtS;
    private JScrollPane scrollPane;
    private JButton btnProcesar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitulo = new JLabel("Lavadora - Procesar datos fijos");
        lblTitulo.setBounds(10, 11, 200, 14);
        contentPane.add(lblTitulo);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 36, 514, 280);
        contentPane.add(scrollPane);

        txtS = new JTextArea();
        scrollPane.setViewportView(txtS);

        btnProcesar = new JButton("Procesar");
        btnProcesar.addActionListener(this);
        btnProcesar.setBounds(10, 327, 100, 23);
        contentPane.add(btnProcesar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProcesar) {
            actionPerformedBtnProcesar(e);
        }
    }

    protected void actionPerformedBtnProcesar(ActionEvent e) {
        procesar();
    }

    /*=======================================================*/
    /*              CODIGO DE ESTUDIANTE                    */
    /*=======================================================*/

    void procesar() {
        // Crear objeto lavadora con datos fijos
        Lavadora lav = new Lavadora(101, 0.67, 1.2, 0.75);

        // Mostrar datos iniciales
        mostrarLavadora(lav);

        // Incrementar ancho en 5% y reducir alto en 10%
        print("Modificnado ancho +5% y alto -10%" + "\n");
        lav.setAncho(lav.getAncho() * 1.05);
        lav.setAlto(lav.getAlto() * 0.9);

        // Mostrar nuevamente los datos incluyendo el tipo
        
        mostrarLavadora(lav);
    }

    // Método que recibe la dirección del objeto y muestra sus datos
    void mostrarLavadora(Lavadora lav) {
        print("Dirección : " + lav);
        print(lav.getDatos());        
    }

    void print() {
        print("");
    }

    void print(String string) {
        txtS.append(string + "\n");
    }
}