package ui;

import javax.swing.*;
import java.awt.*;
import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.MatriculaDAO;
import model.Alumno;
import model.Curso;
import model.Matricula;
import java.util.List;

public class ReportesFrame extends JFrame {
    private JTable tablaReporte;
    private JButton btnAlumnos, btnCursos, btnMatriculas, btnVolver;
    private AlumnoDAO alumnoDAO;
    private CursoDAO cursoDAO;
    private MatriculaDAO matriculaDAO;

    public ReportesFrame() {
        alumnoDAO = new AlumnoDAO();
        cursoDAO = new CursoDAO();
        matriculaDAO = new MatriculaDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Reportes");
        setSize(1100, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBounds(10, 10, 1060, 60);

        btnAlumnos = new JButton("Reporte de Alumnos");
        btnAlumnos.setPreferredSize(new Dimension(180, 40));
        panelBotones.add(btnAlumnos);

        btnCursos = new JButton("Reporte de Cursos");
        btnCursos.setPreferredSize(new Dimension(180, 40));
        panelBotones.add(btnCursos);

        btnMatriculas = new JButton("Reporte de Matrículas");
        btnMatriculas.setPreferredSize(new Dimension(180, 40));
        panelBotones.add(btnMatriculas);

        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(120, 40));
        panelBotones.add(btnVolver);

        add(panelBotones);

        String[] columnas = {"Columna 1", "Columna 2", "Columna 3", "Columna 4", "Columna 5"};
        tablaReporte = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaReporte);
        scrollPane.setBounds(10, 80, 1060, 560);
        add(scrollPane);

        btnAlumnos.addActionListener(e -> reporteAlumnos());
        btnCursos.addActionListener(e -> reporteCursos());
        btnMatriculas.addActionListener(e -> reporteMatriculas());
        btnVolver.addActionListener(e -> dispose());
    }

    private void reporteAlumnos() {
        String[] columnas = {"Código", "DNI", "Apellidos y Nombres", "Fecha Nac.", "Email", "Teléfono", "Dirección"};
        List<Alumno> lista = alumnoDAO.listar();
        Object[][] datos = new Object[lista.size()][7];
        
        for (int i = 0; i < lista.size(); i++) {
            Alumno a = lista.get(i);
            datos[i][0] = a.getCodigo();
            datos[i][1] = a.getDni();
            datos[i][2] = a.getApellido1() + " " + a.getApellido2() + ", " + a.getNombre1() + " " + a.getNombre2();
            datos[i][3] = a.getFechaNacimiento();
            datos[i][4] = a.getEmail();
            datos[i][5] = a.getTelefono();
            datos[i][6] = a.getDireccionDistrito() + ", " + a.getDireccionProvincia();
        }
        
        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        JOptionPane.showMessageDialog(this, "Total de alumnos: " + lista.size());
    }

    private void reporteCursos() {
        String[] columnas = {"Código", "Nombre", "Créditos", "Modalidad", "Carrera"};
        List<Curso> lista = cursoDAO.listar();
        Object[][] datos = new Object[lista.size()][5];
        
        for (int i = 0; i < lista.size(); i++) {
            Curso c = lista.get(i);
            datos[i][0] = c.getCodigo();
            datos[i][1] = c.getNombre();
            datos[i][2] = c.getCreditos();
            datos[i][3] = c.getModalidad();
            datos[i][4] = c.getCarreraCodigo();
        }
        
        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        JOptionPane.showMessageDialog(this, "Total de cursos: " + lista.size());
    }

    private void reporteMatriculas() {
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
        
        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        JOptionPane.showMessageDialog(this, "Total de matrículas: " + lista.size());
    }
}
