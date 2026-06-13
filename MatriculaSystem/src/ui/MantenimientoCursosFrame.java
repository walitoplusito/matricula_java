package ui;

import javax.swing.*;
import java.awt.*;
import dao.CursoDAO;
import dao.CarreraDAO;
import model.Curso;
import model.Carrera;
import util.ArchivoUtil;
import java.util.List;

public class MantenimientoCursosFrame extends JFrame {
    private JTextField txtCodigo, txtNombre, txtCreditos;
    private JComboBox<String> cbModalidad, cbCarreras;
    private JButton btnNuevo, btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btnVolver;
    private JTable tablaCursos;
    private CursoDAO cursoDAO;
    private CarreraDAO carreraDAO;
    private Curso cursoSeleccionado;

    public MantenimientoCursosFrame() {
        cursoDAO = new CursoDAO();
        carreraDAO = new CarreraDAO();
        initComponents();
        cargarCarreras();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("Mantenimiento de Cursos");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(10, 10, 860, 200);
        
        JLabel lblTitulo = new JLabel("DATOS DEL CURSO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(350, 10, 200, 25);
        panelDatos.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(50, 50, 80, 25);
        panelDatos.add(lblCodigo);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 50, 150, 25);
        txtCodigo.setEditable(false);
        panelDatos.add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(350, 50, 80, 25);
        panelDatos.add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(430, 50, 380, 25);
        panelDatos.add(txtNombre);

        JLabel lblCreditos = new JLabel("Créditos:");
        lblCreditos.setBounds(50, 90, 80, 25);
        panelDatos.add(lblCreditos);
        txtCreditos = new JTextField();
        txtCreditos.setBounds(130, 90, 100, 25);
        panelDatos.add(txtCreditos);

        JLabel lblModalidad = new JLabel("Modalidad:");
        lblModalidad.setBounds(300, 90, 80, 25);
        panelDatos.add(lblModalidad);
        cbModalidad = new JComboBox<>(new String[]{"Presencial", "Semi-presencial", "Virtual"});
        cbModalidad.setBounds(390, 90, 150, 25);
        panelDatos.add(cbModalidad);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(600, 90, 60, 25);
        panelDatos.add(lblCarrera);
        cbCarreras = new JComboBox<>();
        cbCarreras.setBounds(670, 90, 180, 25);
        panelDatos.add(cbCarreras);

        add(panelDatos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBounds(10, 220, 860, 50);

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

        String[] columnas = {"Código", "Nombre", "Créditos", "Modalidad", "Carrera"};
        tablaCursos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        scrollPane.setBounds(10, 280, 860, 260);
        add(scrollPane);

        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnVolver.addActionListener(e -> dispose());

        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarDeTabla();
            }
        });
    }

    private void cargarCarreras() {
        cbCarreras.removeAllItems();
        List<Carrera> lista = carreraDAO.listar();
        for (Carrera c : lista) {
            cbCarreras.addItem(c.getCodigo() + " - " + c.getNombre());
        }
    }

    private void cargarTabla() {
        String[] columnas = {"Código", "Nombre", "Créditos", "Modalidad", "Carrera"};
        List<Curso> lista = cursoDAO.listar();
        Object[][] datos = new Object[lista.size()][5];
        
        for (int i = 0; i < lista.size(); i++) {
            Curso c = lista.get(i);
            Carrera carrera = carreraDAO.buscarPorCodigo(c.getCarreraCodigo());
            String nombreCarrera = carrera != null ? carrera.getNombre() : c.getCarreraCodigo();
            datos[i][0] = c.getCodigo();
            datos[i][1] = c.getNombre();
            datos[i][2] = c.getCreditos();
            datos[i][3] = c.getModalidad();
            datos[i][4] = nombreCarrera;
        }
        
        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void nuevo() {
        limpiar();
        List<Curso> lista = cursoDAO.listar();
        String correlativo = ArchivoUtil.generarCorrelativo(lista.size(), 5);
        txtCodigo.setText("CUR" + correlativo);
        txtNombre.requestFocus();
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete el nombre del curso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Curso curso = new Curso();
        curso.setCodigo(txtCodigo.getText());
        curso.setNombre(txtNombre.getText());
        curso.setCreditos(txtCreditos.getText());
        curso.setModalidad(cbModalidad.getSelectedItem().toString());
        
        String carreraSel = cbCarreras.getSelectedItem() != null ? cbCarreras.getSelectedItem().toString() : "";
        if (!carreraSel.isEmpty()) {
            curso.setCarreraCodigo(carreraSel.split(" - ")[0]);
        }

        if (cursoDAO.guardar(curso)) {
            JOptionPane.showMessageDialog(this, "Curso guardado exitosamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        if (cursoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso de la tabla");
            return;
        }
        guardar();
    }

    private void eliminar() {
        if (cursoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso de la tabla");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar curso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (cursoDAO.eliminar(cursoSeleccionado.getCodigo())) {
                JOptionPane.showMessageDialog(this, "Curso eliminado");
                cargarTabla();
                limpiar();
            }
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de curso:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Curso curso = cursoDAO.buscarPorCodigo(codigo);
            if (curso != null) {
                mostrarCurso(curso);
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado");
            }
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCreditos.setText("");
        cbModalidad.setSelectedIndex(0);
        cursoSeleccionado = null;
    }

    private void mostrarCurso(Curso curso) {
        txtCodigo.setText(curso.getCodigo());
        txtNombre.setText(curso.getNombre());
        txtCreditos.setText(curso.getCreditos());
        cbModalidad.setSelectedItem(curso.getModalidad());
        
        Carrera carrera = carreraDAO.buscarPorCodigo(curso.getCarreraCodigo());
        if (carrera != null) {
            cbCarreras.setSelectedItem(curso.getCarreraCodigo() + " - " + carrera.getNombre());
        }
        cursoSeleccionado = curso;
    }

    private void seleccionarDeTabla() {
        int fila = tablaCursos.getSelectedRow();
        if (fila >= 0) {
            String codigo = tablaCursos.getValueAt(fila, 0).toString();
            Curso curso = cursoDAO.buscarPorCodigo(codigo);
            if (curso != null) {
                mostrarCurso(curso);
            }
        }
    }
}
