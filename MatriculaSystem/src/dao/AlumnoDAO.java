package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Alumno;

public class AlumnoDAO {
    private static final String ARCHIVO = "data/alumnos.txt";

    public List<Alumno> listar() {
        List<Alumno> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Alumno alumno = Alumno.fromString(linea);
                    if (alumno != null) lista.add(alumno);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardar(Alumno alumno) {
        List<Alumno> lista = listar();
        for (Alumno a : lista) {
            if (a.getCodigo().equals(alumno.getCodigo())) {
                return actualizar(alumno);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(alumno.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Alumno alumnoNuevo) {
        List<Alumno> lista = listar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(alumnoNuevo.getCodigo())) {
                lista.set(i, alumnoNuevo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Alumno a : lista) {
                pw.println(a.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        List<Alumno> lista = listar();
        boolean eliminado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(codigo)) {
                lista.remove(i);
                eliminado = true;
                break;
            }
        }
        if (!eliminado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Alumno a : lista) {
                pw.println(a.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Alumno buscarPorDni(String dni) {
        List<Alumno> lista = listar();
        for (Alumno a : lista) {
            if (a.getDni().equals(dni)) {
                return a;
            }
        }
        return null;
    }

    public Alumno buscarPorCodigo(String codigo) {
        List<Alumno> lista = listar();
        for (Alumno a : lista) {
            if (a.getCodigo().equals(codigo)) {
                return a;
            }
        }
        return null;
    }
}
