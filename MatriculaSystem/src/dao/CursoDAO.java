package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

public class CursoDAO {
    private static final String ARCHIVO = "data/cursos.txt";

    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Curso curso = Curso.fromString(linea);
                    if (curso != null) lista.add(curso);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardar(Curso curso) {
        List<Curso> lista = listar();
        for (Curso c : lista) {
            if (c.getCodigo().equals(curso.getCodigo())) {
                return actualizar(curso);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(curso.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Curso cursoNuevo) {
        List<Curso> lista = listar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(cursoNuevo.getCodigo())) {
                lista.set(i, cursoNuevo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Curso c : lista) {
                pw.println(c.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        List<Curso> lista = listar();
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
            for (Curso c : lista) {
                pw.println(c.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Curso buscarPorCodigo(String codigo) {
        List<Curso> lista = listar();
        for (Curso c : lista) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }
}
