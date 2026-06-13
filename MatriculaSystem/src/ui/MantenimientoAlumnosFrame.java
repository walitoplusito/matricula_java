package ui;

import javax.swing.*;
import java.awt.*;
import dao.AlumnoDAO;
import model.Alumno;
import util.ArchivoUtil;
import java.util.List;

public class MantenimientoAlumnosFrame extends JFrame {
    private JTextField txtCodigo, txtDni, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JTextField txtFechaNacimiento, txtDistrito, txtProvincia, txtDepartamento, txtPais;
    private JTextField txtEmail, txtTelefono;
    private JButton btnNuevo, btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btnVolver;
    private JTable tablaAlumnos;
    private AlumnoDAO alumnoDAO;
    private Alumno alumnoSeleccionado;

    public MantenimientoAlumnosFrame() {
        alumnoDAO = new AlumnoDAO();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("Mantenimiento de Alumnos");
        setSize(1200, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel superior - Datos del alumno
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(10, 10, 1160, 280);
        
        JLabel lblTitulo = new JLabel("DATOS DEL ALUMNO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(500, 10, 200, 25);
        panelDatos.add(lblTitulo);

        // Fila 1
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 40, 80, 25);
        panelDatos.add(lblCodigo);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 40, 150, 25);
        txtCodigo.setEditable(false);
        panelDatos.add(txtCodigo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(300, 40, 80, 25);
        panelDatos.add(lblDni);
        txtDni = new JTextField();
        txtDni.setBounds(380, 40, 150, 25);
        panelDatos.add(txtDni);

        JLabel lblFecha = new JLabel("F. Nacimiento:");
        lblFecha.setBounds(580, 40, 100, 25);
        panelDatos.add(lblFecha);
        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setBounds(690, 40, 150, 25);
        panelDatos.add(txtFechaNacimiento);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(880, 40, 80, 25);
        panelDatos.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(960, 40, 180, 25);
        panelDatos.add(txtEmail);

        // Fila 2
        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(20, 80, 100, 25);
        panelDatos.add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(130, 80, 200, 25);
        panelDatos.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(380, 80, 120, 25);
        panelDatos.add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(510, 80, 200, 25);
        panelDatos.add(txtNombre2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(750, 80, 80, 25);
        panelDatos.add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(840, 80, 150, 25);
        panelDatos.add(txtTelefono);

        // Fila 3
        JLabel lblApellido1 = new JLabel("Apellido Paterno:");
        lblApellido1.setBounds(20, 120, 120, 25);
        panelDatos.add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(150, 120, 200, 25);
        panelDatos.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Apellido Materno:");
        lblApellido2.setBounds(400, 120, 120, 25);
        panelDatos.add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(530, 120, 200, 25);
        panelDatos.add(txtApellido2);

        // Fila 4 - Dirección
        JLabel lblDireccion = new JLabel("DIRECCIÓN");
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 14));
        lblDireccion.setBounds(500, 160, 150, 25);
        panelDatos.add(lblDireccion);

        JLabel lblDistrito = new JLabel("Distrito:");
        lblDistrito.setBounds(20, 190, 80, 25);
        panelDatos.add(lblDistrito);
        txtDistrito = new JTextField();
        txtDistrito.setBounds(100, 190, 180, 25);
        panelDatos.add(txtDistrito);

        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setBounds(320, 190, 80, 25);
        panelDatos.add(lblProvincia);
        txtProvincia = new JTextField();
        txtProvincia.setBounds(400, 190, 180, 25);
        panelDatos.add(txtProvincia);

        JLabel lblDepartamento = new JLabel("Departamento:");
        lblDepartamento.setBounds(620, 190, 100, 25);
        panelDatos.add(lblDepartamento);
        txtDepartamento = new JTextField();
        txtDepartamento.setBounds(730, 190, 180, 25);
        panelDatos.add(txtDepartamento);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(950, 190, 50, 25);
        panelDatos.add(lblPais);
        txtPais = new JTextField();
        txtPais.setBounds(1010, 190, 130, 25);
        panelDatos.add(txtPais);

        add(panelDatos);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBounds(10, 300, 1160, 50);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnNuevo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnGuardar);

        btnEditar = new JButton("Editar");
        btnEditar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnEliminar);

        btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setPreferredSize(new Dimension(120, 30));
        panelBotones.add(btnBuscar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnLimpiar);

        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnVolver);

        add(panelBotones);

        // Tabla
        String[] columnas = {"Código", "DNI", "Apellidos y Nombres", "Email", "Teléfono", "Distrito"};
        tablaAlumnos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        scrollPane.setBounds(10, 360, 1160, 280);
        add(scrollPane);

        // Eventos
        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnVolver.addActionListener(e -> dispose());

        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarDeTabla();
            }
        });
    }

    private void cargarTabla() {
        String[] columnas = {"Código", "DNI", "Apellidos y Nombres", "Email", "Teléfono", "Distrito"};
        List<Alumno> lista = alumnoDAO.listar();
        Object[][] datos = new Object[lista.size()][6];
        
        for (int i = 0; i < lista.size(); i++) {
            Alumno a = lista.get(i);
            datos[i][0] = a.getCodigo();
            datos[i][1] = a.getDni();
            datos[i][2] = a.getApellido1() + " " + a.getApellido2() + ", " + a.getNombre1() + " " + a.getNombre2();
            datos[i][3] = a.getEmail();
            datos[i][4] = a.getTelefono();
            datos[i][5] = a.getDireccionDistrito();
        }
        
        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void nuevo() {
        limpiar();
        List<Alumno> lista = alumnoDAO.listar();
        String correlativo = ArchivoUtil.generarCorrelativo(lista.size(), 9);
        int anio = java.time.Year.now().getValue();
        txtCodigo.setText(anio + correlativo);
        txtDni.requestFocus();
    }

    private void guardar() {
        if (txtDni.getText().trim().isEmpty() || txtNombre1.getText().trim().isEmpty() || 
            txtApellido1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumno = new Alumno();
        alumno.setCodigo(txtCodigo.getText());
        alumno.setDni(txtDni.getText());
        alumno.setNombre1(txtNombre1.getText());
        alumno.setNombre2(txtNombre2.getText());
        alumno.setApellido1(txtApellido1.getText());
        alumno.setApellido2(txtApellido2.getText());
        alumno.setFechaNacimiento(txtFechaNacimiento.getText());
        alumno.setDireccionDistrito(txtDistrito.getText());
        alumno.setDireccionProvincia(txtProvincia.getText());
        alumno.setDireccionDepartamento(txtDepartamento.getText());
        alumno.setDireccionPais(txtPais.getText());
        alumno.setEmail(txtEmail.getText());
        alumno.setTelefono(txtTelefono.getText());

        if (alumnoDAO.guardar(alumno)) {
            JOptionPane.showMessageDialog(this, "Alumno guardado exitosamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        if (alumnoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno de la tabla");
            return;
        }
        guardar();
    }

    private void eliminar() {
        if (alumnoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno de la tabla");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar alumno?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (alumnoDAO.eliminar(alumnoSeleccionado.getCodigo())) {
                JOptionPane.showMessageDialog(this, "Alumno eliminado");
                cargarTabla();
                limpiar();
            }
        }
    }

    private void buscar() {
        String dni = JOptionPane.showInputDialog(this, "Ingrese DNI:");
        if (dni != null && !dni.trim().isEmpty()) {
            Alumno alumno = alumnoDAO.buscarPorDni(dni);
            if (alumno != null) {
                mostrarAlumno(alumno);
            } else {
                JOptionPane.showMessageDialog(this, "Alumno no encontrado");
            }
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtDni.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtFechaNacimiento.setText("");
        txtDistrito.setText("");
        txtProvincia.setText("");
        txtDepartamento.setText("");
        txtPais.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        alumnoSeleccionado = null;
    }

    private void mostrarAlumno(Alumno alumno) {
        txtCodigo.setText(alumno.getCodigo());
        txtDni.setText(alumno.getDni());
        txtNombre1.setText(alumno.getNombre1());
        txtNombre2.setText(alumno.getNombre2());
        txtApellido1.setText(alumno.getApellido1());
        txtApellido2.setText(alumno.getApellido2());
        txtFechaNacimiento.setText(alumno.getFechaNacimiento());
        txtDistrito.setText(alumno.getDireccionDistrito());
        txtProvincia.setText(alumno.getDireccionProvincia());
        txtDepartamento.setText(alumno.getDireccionDepartamento());
        txtPais.setText(alumno.getDireccionPais());
        txtEmail.setText(alumno.getEmail());
        txtTelefono.setText(alumno.getTelefono());
        alumnoSeleccionado = alumno;
    }

    private void seleccionarDeTabla() {
        int fila = tablaAlumnos.getSelectedRow();
        if (fila >= 0) {
            String codigo = tablaAlumnos.getValueAt(fila, 0).toString();
            Alumno alumno = alumnoDAO.buscarPorCodigo(codigo);
            if (alumno != null) {
                mostrarAlumno(alumno);
            }
        }
    }
}
