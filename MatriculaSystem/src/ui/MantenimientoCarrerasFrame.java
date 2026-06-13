package ui;

import javax.swing.*;
import java.awt.*;
import dao.CarreraDAO;
import model.Carrera;
import util.ArchivoUtil;
import java.util.List;

public class MantenimientoCarrerasFrame extends JFrame {
    private JTextField txtCodigo, txtNombre, txtFacultad, txtDuracion;
    private JButton btnNuevo, btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btnVolver;
    private JTable tablaCarreras;
    private CarreraDAO carreraDAO;
    private Carrera carreraSeleccionada;

    public MantenimientoCarrerasFrame() {
        carreraDAO = new CarreraDAO();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("Mantenimiento de Carreras");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(10, 10, 860, 180);
        
        JLabel lblTitulo = new JLabel("DATOS DE LA CARRERA");
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

        JLabel lblFacultad = new JLabel("Facultad:");
        lblFacultad.setBounds(50, 90, 80, 25);
        panelDatos.add(lblFacultad);
        txtFacultad = new JTextField();
        txtFacultad.setBounds(130, 90, 300, 25);
        panelDatos.add(txtFacultad);

        JLabel lblDuracion = new JLabel("Duración (años):");
        lblDuracion.setBounds(500, 90, 100, 25);
        panelDatos.add(lblDuracion);
        txtDuracion = new JTextField();
        txtDuracion.setBounds(610, 90, 100, 25);
        panelDatos.add(txtDuracion);

        add(panelDatos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBounds(10, 200, 860, 50);

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

        String[] columnas = {"Código", "Nombre", "Facultad", "Duración"};
        tablaCarreras = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaCarreras);
        scrollPane.setBounds(10, 260, 860, 280);
        add(scrollPane);

        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnVolver.addActionListener(e -> dispose());

        tablaCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarDeTabla();
            }
        });
    }

    private void cargarTabla() {
        String[] columnas = {"Código", "Nombre", "Facultad", "Duración"};
        List<Carrera> lista = carreraDAO.listar();
        Object[][] datos = new Object[lista.size()][4];
        
        for (int i = 0; i < lista.size(); i++) {
            Carrera c = lista.get(i);
            datos[i][0] = c.getCodigo();
            datos[i][1] = c.getNombre();
            datos[i][2] = c.getFacultad();
            datos[i][3] = c.getDuracionAnios();
        }
        
        tablaCarreras.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void nuevo() {
        limpiar();
        List<Carrera> lista = carreraDAO.listar();
        String correlativo = ArchivoUtil.generarCorrelativo(lista.size(), 4);
        txtCodigo.setText("C" + correlativo);
        txtNombre.requestFocus();
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty() || txtFacultad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Carrera carrera = new Carrera();
        carrera.setCodigo(txtCodigo.getText());
        carrera.setNombre(txtNombre.getText());
        carrera.setFacultad(txtFacultad.getText());
        carrera.setDuracionAnios(txtDuracion.getText());

        if (carreraDAO.guardar(carrera)) {
            JOptionPane.showMessageDialog(this, "Carrera guardada exitosamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        if (carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera de la tabla");
            return;
        }
        guardar();
    }

    private void eliminar() {
        if (carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera de la tabla");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar carrera?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (carreraDAO.eliminar(carreraSeleccionada.getCodigo())) {
                JOptionPane.showMessageDialog(this, "Carrera eliminada");
                cargarTabla();
                limpiar();
            }
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese código de carrera:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Carrera carrera = carreraDAO.buscarPorCodigo(codigo);
            if (carrera != null) {
                mostrarCarrera(carrera);
            } else {
                JOptionPane.showMessageDialog(this, "Carrera no encontrada");
            }
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtFacultad.setText("");
        txtDuracion.setText("");
        carreraSeleccionada = null;
    }

    private void mostrarCarrera(Carrera carrera) {
        txtCodigo.setText(carrera.getCodigo());
        txtNombre.setText(carrera.getNombre());
        txtFacultad.setText(carrera.getFacultad());
        txtDuracion.setText(carrera.getDuracionAnios());
        carreraSeleccionada = carrera;
    }

    private void seleccionarDeTabla() {
        int fila = tablaCarreras.getSelectedRow();
        if (fila >= 0) {
            String codigo = tablaCarreras.getValueAt(fila, 0).toString();
            Carrera carrera = carreraDAO.buscarPorCodigo(codigo);
            if (carrera != null) {
                mostrarCarrera(carrera);
            }
        }
    }
}
