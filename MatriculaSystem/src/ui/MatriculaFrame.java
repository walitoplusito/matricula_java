package ui;

import javax.swing.*;
import java.awt.*;
import dao.MatriculaDAO;
import dao.AlumnoDAO;
import dao.CursoDAO;
import model.Matricula;
import model.Alumno;
import model.Curso;
import util.ArchivoUtil;
import java.util.List;

public class MatriculaFrame extends JFrame {
    private JTextField txtCodigoMatricula, txtAlumno, txtCurso, txtPeriodo, txtNota;
    private JButton btnNuevo, btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btnVolver;
    private JTable tablaMatriculas;
    private MatriculaDAO matriculaDAO;
    private AlumnoDAO alumnoDAO;
    private CursoDAO cursoDAO;
    private Matricula matriculaSeleccionada;

    public MatriculaFrame() {
        matriculaDAO = new MatriculaDAO();
        alumnoDAO = new AlumnoDAO();
        cursoDAO = new CursoDAO();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("Registro de Matrículas");
        setSize(1000, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(10, 10, 960, 180);
        
        JLabel lblTitulo = new JLabel("REGISTRO DE MATRÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(380, 10, 250, 25);
        panelDatos.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Cód. Matrícula:");
        lblCodigo.setBounds(30, 50, 100, 25);
        panelDatos.add(lblCodigo);
        txtCodigoMatricula = new JTextField();
        txtCodigoMatricula.setBounds(140, 50, 120, 25);
        txtCodigoMatricula.setEditable(false);
        panelDatos.add(txtCodigoMatricula);

        JLabel lblPeriodo = new JLabel("Periodo:");
        lblPeriodo.setBounds(320, 50, 70, 25);
        panelDatos.add(lblPeriodo);
        txtPeriodo = new JTextField();
        txtPeriodo.setBounds(390, 50, 120, 25);
        panelDatos.add(txtPeriodo);

        JLabel lblAlumno = new JLabel("Alumno:");
        lblAlumno.setBounds(30, 90, 70, 25);
        panelDatos.add(lblAlumno);
        txtAlumno = new JTextField();
        txtAlumno.setBounds(100, 90, 250, 25);
        panelDatos.add(txtAlumno);
        
        JButton btnBuscarAlumno = new JButton("...");
        btnBuscarAlumno.setBounds(360, 90, 40, 25);
        panelDatos.add(btnBuscarAlumno);

        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(450, 90, 50, 25);
        panelDatos.add(lblCurso);
        txtCurso = new JTextField();
        txtCurso.setBounds(500, 90, 250, 25);
        panelDatos.add(txtCurso);
        
        JButton btnBuscarCurso = new JButton("...");
        btnBuscarCurso.setBounds(760, 90, 40, 25);
        panelDatos.add(btnBuscarCurso);

        JLabel lblNota = new JLabel("Nota:");
        lblNota.setBounds(30, 130, 50, 25);
        panelDatos.add(lblNota);
        txtNota = new JTextField();
        txtNota.setBounds(80, 130, 80, 25);
        panelDatos.add(txtNota);

        add(panelDatos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBounds(10, 200, 960, 50);

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

        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnBuscar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnLimpiar);

        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 30));
        panelBotones.add(btnVolver);

        add(panelBotones);

        String[] columnas = {"Cód.Matrícula", "Alumno", "Curso", "Periodo", "Nota"};
        tablaMatriculas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaMatriculas);
        scrollPane.setBounds(10, 260, 960, 330);
        add(scrollPane);

        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnVolver.addActionListener(e -> dispose());
        
        btnBuscarAlumno.addActionListener(e -> buscarAlumno());
        btnBuscarCurso.addActionListener(e -> buscarCurso());

        tablaMatriculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarDeTabla();
            }
        });
    }

    private void cargarTabla() {
        String[] columnas = {"Cód.Matrícula", "Alumno", "Curso", "Periodo", "Nota"};
        List<Matricula> lista = matriculaDAO.listar();
        Object[][] datos = new Object[lista.size()][5];
        
        for (int i = 0; i < lista.size(); i++) {
            Matricula m = lista.get(i);
            Alumno alumno = alumnoDAO.buscarPorCodigo(m.getAlumnoCodigo());
            Curso curso = cursoDAO.buscarPorCodigo(m.getCursoCodigo());
            String nombreAlumno = alumno != null ? alumno.getApellido1() + " " + alumno.getNombre1() : m.getAlumnoCodigo();
            String nombreCurso = curso != null ? curso.getNombre() : m.getCursoCodigo();
            datos[i][0] = m.getCodigoMatricula();
            datos[i][1] = nombreAlumno;
            datos[i][2] = nombreCurso;
            datos[i][3] = m.getPeriodo();
            datos[i][4] = m.getNota() != null ? m.getNota() : "-";
        }
        
        tablaMatriculas.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void nuevo() {
        limpiar();
        List<Matricula> lista = matriculaDAO.listar();
        String correlativo = ArchivoUtil.generarCorrelativo(lista.size(), 6);
        txtCodigoMatricula.setText(correlativo);
        txtPeriodo.setText(java.time.Year.now().getValue() + "-I");
        txtAlumno.requestFocus();
    }

    private void guardar() {
        if (txtAlumno.getText().trim().isEmpty() || txtCurso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione alumno y curso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Matricula matricula = new Matricula();
        matricula.setCodigoMatricula(txtCodigoMatricula.getText());
        matricula.setAlumnoCodigo(txtAlumno.getText().split(" - ")[0]);
        matricula.setCursoCodigo(txtCurso.getText().split(" - ")[0]);
        matricula.setPeriodo(txtPeriodo.getText());
        matricula.setNota(txtNota.getText().trim().isEmpty() ? "0" : txtNota.getText());

        if (matriculaDAO.guardar(matricula)) {
            JOptionPane.showMessageDialog(this, "Matrícula guardada exitosamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        if (matriculaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una matrícula de la tabla");
            return;
        }
        guardar();
    }

    private void eliminar() {
        if (matriculaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una matrícula de la tabla");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar matrícula?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (matriculaDAO.eliminar(matriculaSeleccionada.getCodigoMatricula())) {
                JOptionPane.showMessageDialog(this, "Matrícula eliminada");
                cargarTabla();
                limpiar();
            }
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de matrícula:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Matricula matricula = matriculaDAO.buscarPorCodigo(codigo);
            if (matricula != null) {
                mostrarMatricula(matricula);
            } else {
                JOptionPane.showMessageDialog(this, "Matrícula no encontrada");
            }
        }
    }

    private void buscarAlumno() {
        String dni = JOptionPane.showInputDialog(this, "Ingrese DNI del alumno:");
        if (dni != null && !dni.trim().isEmpty()) {
            Alumno alumno = alumnoDAO.buscarPorDni(dni);
            if (alumno != null) {
                txtAlumno.setText(alumno.getCodigo() + " - " + alumno.getApellido1() + " " + alumno.getApellido2() + ", " + alumno.getNombre1());
            } else {
                JOptionPane.showMessageDialog(this, "Alumno no encontrado");
            }
        }
    }

    private void buscarCurso() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de curso:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Curso curso = cursoDAO.buscarPorCodigo(codigo);
            if (curso != null) {
                txtCurso.setText(curso.getCodigo() + " - " + curso.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado");
            }
        }
    }

    private void limpiar() {
        txtCodigoMatricula.setText("");
        txtAlumno.setText("");
        txtCurso.setText("");
        txtPeriodo.setText("");
        txtNota.setText("");
        matriculaSeleccionada = null;
    }

    private void mostrarMatricula(Matricula matricula) {
        txtCodigoMatricula.setText(matricula.getCodigoMatricula());
        txtPeriodo.setText(matricula.getPeriodo());
        txtNota.setText(matricula.getNota() != null ? matricula.getNota() : "");
        
        Alumno alumno = alumnoDAO.buscarPorCodigo(matricula.getAlumnoCodigo());
        if (alumno != null) {
            txtAlumno.setText(alumno.getCodigo() + " - " + alumno.getApellido1() + " " + alumno.getApellido2() + ", " + alumno.getNombre1());
        }
        
        Curso curso = cursoDAO.buscarPorCodigo(matricula.getCursoCodigo());
        if (curso != null) {
            txtCurso.setText(curso.getCodigo() + " - " + curso.getNombre());
        }
        matriculaSeleccionada = matricula;
    }

    private void seleccionarDeTabla() {
        int fila = tablaMatriculas.getSelectedRow();
        if (fila >= 0) {
            String codigo = tablaMatriculas.getValueAt(fila, 0).toString();
            Matricula matricula = matriculaDAO.buscarPorCodigo(codigo);
            if (matricula != null) {
                mostrarMatricula(matricula);
            }
        }
    }
}
