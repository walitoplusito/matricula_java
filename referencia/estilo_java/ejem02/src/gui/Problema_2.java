/* Pregunta 2 (13 puntos)
 * En la clase Problema_2, a la pulsación del botón Procesar: 
1. Declare, cree e inicialice tres objetos de tipo Consultor (con datos fijos) haciendo uso de los tres constructores. 
2. Invoque a un método listado que reciba como parámetro la dirección de memoria del objeto Consultor y visualice sus datos completos. 
3. Muestre el valor de los factores de descuentos por AFP y EPS, la cantidad de objetos creados y el acumulado de los sueldos netos.
*/


package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import examen.Consultor;

public class Problema_2 extends JFrame implements ActionListener {

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
                    Problema_2 frame = new Problema_2();
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
    public Problema_2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitulo = new JLabel("Consultor - Datos fijos");
        lblTitulo.setBounds(10, 11, 150, 14);
        contentPane.add(lblTitulo);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 36, 564, 380);
        contentPane.add(scrollPane);

        txtS = new JTextArea();
        scrollPane.setViewportView(txtS);

        btnProcesar = new JButton("Procesar");
        btnProcesar.addActionListener(this);
        btnProcesar.setBounds(10, 427, 100, 23);
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
        // Inicializa
        txtS.setText("");

        // 1. Crear tres objetos usando los tres constructores (datos fijos)
        Consultor c1 = new Consultor(101, "Edward", 40, 80.0);
        Consultor c2 = new Consultor(102, "Leito", 35);       
        Consultor c3 = new Consultor();                       

        // 2. Mostrar cada objeto mediante método listado
        listado(c1);
        listado(c2);
        listado(c3);

        // 3. Mostrar factores de descuento, cantidad de objetos y acumulado de sueldos netos
        print();
        print("=== RESUMEN GENERAL ===");
        print("Factor AFP : " + Consultor.AFP);
        print("Factor EPS : " + Consultor.EPS);
        print("Cantidad de objetos Consultor creados: " + Consultor.getCantidadConsultores());
        print("Acumulado de sueldos netos: S/ " + String.format("%.2f", Consultor.getAcumuladoSueldosNetos()));
    }

    // Método que recibe la dirección del objeto y visualiza sus datos completos
    void listado(Consultor c) {
    	print("=========================================");
    	print("Dirección : " + c);
        print("Código    : " + c.getCodigo());
        print("Nombre    : " + c.getNombre());
        print("Horas     : " + c.getHorasTrabajadas());
        print("Tarifa    : S/ " + c.getTarifaPorHora());
        print("Sueldo Bruto : S/ " + c.sueldoBruto());
        print("Descuento AFP : S/ " + c.descuentoAFP());
        print("Descuento EPS : S/ " + c.descuentoEPS());
        print("Sueldo Neto   : S/ " + c.sueldoNeto());
        print();
    }

    void print() {
        print("");
    }

    void print(String string) {
        txtS.append(string + "\n");
    }
}