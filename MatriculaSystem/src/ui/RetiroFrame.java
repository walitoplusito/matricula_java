package ui;

import javax.swing.*;
import java.awt.*;
import dao.RetiroDAO;
import dao.MatriculaDAO;
import model.Retiro;
import model.Matricula;
import model.Alumno;
import model.Curso;
import dao.AlumnoDAO;
import dao.CursoDAO;
import util.ArchivoUtil;
import java.util.List;

public class RetiroFrame extends JFrame {
    private JTextField txtCodigoRetiro, txtMatricula, txtFecha, txtMotivo;
    private JButton btnNuevo, btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btnVolver, btnBuscarMatricula;
    private JTable tablaRetiros;
    private RetiroDAO retiroDAO;
    private MatriculaDAO matriculaDAO;
    private AlumnoDAO alumnoDAO;
    private CursoDAO cursoDAO;
    private Retiro retiroSeleccionado;

    public RetiroFrame() {
        retiroDAO = new RetiroDAO();
        matriculaDAO = new MatriculaDAO();
        alumnoDAO = new AlumnoDAO();
        cursoDAO = new CursoDAO();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("Registro de Retiros");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(10, 10, 860, 150);
        
        JLabel lblTitulo = new JLabel("REGISTRO DE RETIRO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(350, 10, 200, 25);
        panelDatos.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Cód. Retiro:");
        lblCodigo.setBounds(30, 50, 80, 25);
        panelDatos.add(lblCodigo);
        txtCodigoRetiro = new JTextField();
        txtCodigoRetiro.setBounds(120, 50, 120, 25);
        txtCodigoRetiro.setEditable(false);
        panelDatos.add(txtCodigoRetiro);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(300, 50, 50, 25);
        panelDatos.add(lblFecha);
        txtFecha = new JTextField();
        txtFecha.setBounds(350, 50, 120, 25);
        panelDatos.add(txtFecha);

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(30, 90, 70, 25);
        panelDatos.add(lblMatricula);
        txtMatricula = new JTextField();
        txtMatricula.setBounds(110, 90, 200, 25);
        panelDatos.add(txtMatricula);
        
        btnBuscarMatricula = new JButton("...");
        btnBuscarMatricula.setBounds(320, 90, 40, 25);
        panelDatos.add(btnBuscarMatricula);

        JLabel lblMotivo = new JLabel("Motivo:");
        lblMotivo.setBounds(400, 90, 60, 25);
        panelDatos.add(lblMotivo);
        txtMotivo = new JTextField();
        txtMotivo.setBounds(460, 90, 380, 25);
        panelDatos.add(txtMotivo);

        add(panelDatos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBounds(10, 170, 860, 50);

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

        String[] columnas = {"Cód.Retiro", "Cód.Matrícula", "Fecha", "Motivo"};
        tablaRetiros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaRetiros);
        scrollPane.setBounds(10, 230, 860, 310);
        add(scrollPane);

        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnVolver.addActionListener(e -> dispose());
        btnBuscarMatricula.addActionListener(e -> buscarMatricula());

        tablaRetiros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarDeTabla();
            }
        });
    }

    private void cargarTabla() {
        String[] columnas = {"Cód.Retiro", "Cód.Matrícula", "Fecha", "Motivo"};
        List<Retiro> lista = retiroDAO.listar();
        Object[][] datos = new Object[lista.size()][4];
        
        for (int i = 0; i < lista.size(); i++) {
            Retiro r = lista.get(i);
            datos[i][0] = r.getCodigoRetiro();
            datos[i][1] = r.getCodigoMatricula();
            datos[i][2] = r.getFechaRetiro();
            datos[i][3] = r.getMotivo();
        }
        
        tablaRetiros.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void nuevo() {
        limpiar();
        List<Retiro> lista = retiroDAO.listar();
        String correlativo = ArchivoUtil.generarCorrelativo(lista.size(), 6);
        txtCodigoRetiro.setText("2" + correlativo);
        txtFecha.setText(java.time.LocalDate.now().toString());
        txtMotivo.requestFocus();
    }

    private void guardar() {
        if (txtMatricula.getText().trim().isEmpty() || txtMotivo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete matrícula y motivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Retiro retiro = new Retiro();
        retiro.setCodigoRetiro(txtCodigoRetiro.getText());
        retiro.setCodigoMatricula(txtMatricula.getText());
        retiro.setFechaRetiro(txtFecha.getText());
        retiro.setMotivo(txtMotivo.getText());

        if (retiroDAO.guardar(retiro)) {
            JOptionPane.showMessageDialog(this, "Retiro guardado exitosamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        if (retiroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un retiro de la tabla");
            return;
        }
        guardar();
    }

    private void eliminar() {
        if (retiroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un retiro de la tabla");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar retiro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (retiroDAO.eliminar(retiroSeleccionado.getCodigoRetiro())) {
                JOptionPane.showMessageDialog(this, "Retiro eliminado");
                cargarTabla();
                limpiar();
            }
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de retiro:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Retiro retiro = retiroDAO.buscarPorCodigo(codigo);
            if (retiro != null) {
                mostrarRetiro(retiro);
            } else {
                JOptionPane.showMessageDialog(this, "Retiro no encontrado");
            }
        }
    }

    private void buscarMatricula() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de matrícula:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Matricula matricula = matriculaDAO.buscarPorCodigo(codigo);
            if (matricula != null) {
                Alumno alumno = alumnoDAO.buscarPorCodigo(matricula.getAlumnoCodigo());
                Curso curso = cursoDAO.buscarPorCodigo(matricula.getCursoCodigo());
                String info = matricula.getCodigoMatricula();
                if (alumno != null) info += " - " + alumno.getApellido1() + " " + alumno.getNombre1();
                if (curso != null) info += " - " + curso.getNombre();
                txtMatricula.setText(info);
            } else {
                JOptionPane.showMessageDialog(this, "Matrícula no encontrada");
            }
        }
    }

    private void limpiar() {
        txtCodigoRetiro.setText("");
        txtMatricula.setText("");
        txtFecha.setText("");
        txtMotivo.setText("");
        retiroSeleccionado = null;
    }

    private void mostrarRetiro(Retiro retiro) {
        txtCodigoRetiro.setText(retiro.getCodigoRetiro());
        txtMatricula.setText(retiro.getCodigoMatricula());
        txtFecha.setText(retiro.getFechaRetiro());
        txtMotivo.setText(retiro.getMotivo());
        retiroSeleccionado = retiro;
    }

    private void seleccionarDeTabla() {
        int fila = tablaRetiros.getSelectedRow();
        if (fila >= 0) {
            String codigo = tablaRetiros.getValueAt(fila, 0).toString();
            Retiro retiro = retiroDAO.buscarPorCodigo(codigo);
            if (retiro != null) {
                mostrarRetiro(retiro);
            }
        }
    }
}
