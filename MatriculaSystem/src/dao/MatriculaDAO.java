package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Matricula;

public class MatriculaDAO {
    private static final String ARCHIVO = "data/matriculas.txt";

    public List<Matricula> listar() {
        List<Matricula> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Matricula matricula = Matricula.fromString(linea);
                    if (matricula != null) lista.add(matricula);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardar(Matricula matricula) {
        List<Matricula> lista = listar();
        for (Matricula m : lista) {
            if (m.getCodigoMatricula().equals(matricula.getCodigoMatricula())) {
                return actualizar(matricula);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(matricula.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Matricula matriculaNueva) {
        List<Matricula> lista = listar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoMatricula().equals(matriculaNueva.getCodigoMatricula())) {
                lista.set(i, matriculaNueva);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Matricula m : lista) {
                pw.println(m.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        List<Matricula> lista = listar();
        boolean eliminado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoMatricula().equals(codigo)) {
                lista.remove(i);
                eliminado = true;
                break;
            }
        }
        if (!eliminado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Matricula m : lista) {
                pw.println(m.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Matricula buscarPorCodigo(String codigo) {
        List<Matricula> lista = listar();
        for (Matricula m : lista) {
            if (m.getCodigoMatricula().equals(codigo)) {
                return m;
            }
        }
        return null;
    }
    
    public List<Matricula> buscarPorAlumno(String alumnoCodigo) {
        List<Matricula> lista = listar();
        List<Matricula> resultado = new ArrayList<>();
        for (Matricula m : lista) {
            if (m.getAlumnoCodigo().equals(alumnoCodigo)) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}
