/* En la clase Problema:
01	Ante la pulsación del botón Reemplazar última Estatura menor que 170, invoque al método posicionUltimaEstaturaMenorQue170 e invoque al método listar. En caso de que no exista ninguna Estatura menor que 50, muestre el mensaje "No existe ningún Estatura menor que 170".
02	Ante la pulsación del botón incrementar Estaturas Menores Que 150 invoque al método incrementarEstaturasMenoresQue150 e invoque al método listar. Muestre, además, un mensaje indicando cuantas Estaturas fueron incrementadas.
03	Ante la pulsación del botón Eliminar Ultimo Estatura Menor Que 150, invoque al método eliminarUltimaEstaturaMenorQue150 e invoque al método listar. En caso de que no exista ninguna Estatura menor que 150, muestre el mensaje "No existe ninguna Estatura menor que 150".
*/

package gui;

import java.awt.EventQueue;
import examen.ArregloEstaturas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Problema extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAgregar;
	private JButton btnEliminarAlFinal;
	private JButton btnEliminarTodo;
	private JButton btnReempUltimaEstMenor170;
	private JButton btnIncrementarEstaturasMenoresQue150;
	private JButton btnEliminarUltimaEstatura;
	private JTextField txEstatura;
	private JLabel lblEstatura;
	private JTextArea txS;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	ArregloEstaturas ae = new ArregloEstaturas();
	private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Problema frame = new Problema();
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
	public Problema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(288, 63, 337, 31);
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnAgregar);
		
		btnEliminarAlFinal = new JButton("Eliminar al final");
		btnEliminarAlFinal.addActionListener(this);
		btnEliminarAlFinal.setBounds(288, 112, 337, 31);
		btnEliminarAlFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnEliminarAlFinal);
		
		btnEliminarTodo = new JButton("Eliminar todo");
		btnEliminarTodo.addActionListener(this);
		btnEliminarTodo.setBounds(288, 164, 337, 31);
		btnEliminarTodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnEliminarTodo);
		
		btnReempUltimaEstMenor170 = new JButton("Reemplazar Ultima Estatura Menor Que 170");
		btnReempUltimaEstMenor170.addActionListener(this);
		btnReempUltimaEstMenor170.setBounds(288, 216, 337, 31);
		btnReempUltimaEstMenor170.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnReempUltimaEstMenor170);
		
		btnIncrementarEstaturasMenoresQue150 = new JButton("Incrementar Estaturas Menores Que 150");
		btnIncrementarEstaturasMenoresQue150.addActionListener(this);
		btnIncrementarEstaturasMenoresQue150.setBounds(288, 265, 337, 31);
		btnIncrementarEstaturasMenoresQue150.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnIncrementarEstaturasMenoresQue150);
		
		btnEliminarUltimaEstatura = new JButton("Eliminar Ultima estatura menor que 150");
		btnEliminarUltimaEstatura.addActionListener(this);
		btnEliminarUltimaEstatura.setBounds(288, 317, 337, 31);
		btnEliminarUltimaEstatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnEliminarUltimaEstatura);
		
		txEstatura = new JTextField();
		txEstatura.setBounds(84, 16, 86, 24);
		contentPane.add(txEstatura);
		txEstatura.setColumns(10);
		
		lblEstatura = new JLabel("Estatura");
		lblEstatura.setBounds(10, 11, 64, 31);
		lblEstatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEstatura);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 247, 328);
		contentPane.add(scrollPane);
		
		txS = new JTextArea();
		scrollPane.setViewportView(txS);
		
		lblNewLabel = new JLabel("Cibertec T2 - EDWARD ALDERETE    11-jun-2026");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(304, 370, 321, 14);
		contentPane.add(lblNewLabel);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminarUltimaEstatura) {
			actionPerformedBtnEliminarUltimaEstatura(e);
		}
		if (e.getSource() == btnIncrementarEstaturasMenoresQue150) {
			actionPerformedBtnIncrementarEstaturasMenoresQue150(e);
		}
		if (e.getSource() == btnReempUltimaEstMenor170) {
			actionPerformedBtnReempUltimaEstMenor170(e);
		}
		if (e.getSource() == btnEliminarTodo) {
			actionPerformedBtnEliminarTodo(e);
		}
		if (e.getSource() == btnEliminarAlFinal) {
			actionPerformedBtnEliminarAlFinal(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	
    //===============================================
    //              CODIGO DEL ALUMNO
    //===============================================
	
	
	
	// Se ha mejorado este codigo para aceptar valores válidos
	//evitar cadenas de texto o vacias. estaturas razonables 30 a 250.
	// Try catch básico.
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		int est = leerEstatura();
		if (est>=30 && est<=250) {
			ae.adicionar(est);
			listar();			
		}
		limpiar();		
	}

	protected void actionPerformedBtnEliminarAlFinal(ActionEvent e) {
		ae.eliminarFinal();
		listar();
		limpiar();
	}
	protected void actionPerformedBtnEliminarTodo(ActionEvent e) {
		ae.eliminarTodo();
		listar();
		limpiar();
	}

	
//	01	Ante la pulsación del botón Reemplazar última Estatura menor que 170, 
//	invoque posicionUltimaEstaturaMenorQue170 y listar. En caso de que no exista 
//	muestre el mensaje "No existe ningún Estatura menor que 170".

	protected void actionPerformedBtnReempUltimaEstMenor170(ActionEvent e) {
		int pos=ae.posicionUltimaEstaturaMenorQue170();
		listar();
		
		if(pos==-1)
			mensaje("No existe ningún Estatura menor que 170");
		else
			mensaje("En la posición " + (pos+1));		
		limpiar();
	}


	
//	02	invoque incrementarEstaturasMenoresQue150 y listar. Muestre, además, mensaje 
//	indicando cuantas Estaturas fueron incrementadas.

	protected void actionPerformedBtnIncrementarEstaturasMenoresQue150(ActionEvent e) {
		
		int cont=ae.incrementarEstaturasMenoresQue150();
		listar();

		// se agregó una pequeña mejora para texto en singular/plural		
		if (cont==1) 		
			mensaje("incrementada " + cont + " estatura menor que 150");
		else
			mensaje("incrementadas " + cont + " estaturas menores que 150");
		limpiar();
	}

	
	
//	03	invoque al método eliminarUltimaEstaturaMenorQue150 y listar. Si no existe,
//	muestre el mensaje "No existe ninguna Estatura menor que 150".	
	
	protected void actionPerformedBtnEliminarUltimaEstatura(ActionEvent e) {
		boolean resultado=false;
		resultado=ae.eliminarUltimaEstaturaMenorQue150();
		listar();
		if(!resultado) 
			mensaje("No existe ninguna Estatura menor que 150");		
	}
	
	
	int leerEstatura() {
		int valor=-1;
		try {
			valor=Integer.parseInt(txEstatura.getText().trim());			
		} catch(NumberFormatException e){
			mensaje("debe ingresar un numero válido (30-250");
		}
		
		return valor;	
		
	}
	
	void limpiar() {
		txEstatura.setText("");
		txEstatura.requestFocus();
	}
	
	void listar() {
		txS.setText("");
		for(int i=0; i<ae.tamanio(); i++)
			imprimir("[" + (i+1)+"] " + ae.obtener(i) );
	}
	
	void imprimir() {
		imprimir("");
	}
	
	void imprimir(String s) {
		txS.append(s + "\n");
	}
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

}
