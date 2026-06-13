package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Carrera;

public class CarreraDAO {
    private static final String ARCHIVO = "data/carreras.txt";

    public List<Carrera> listar() {
        List<Carrera> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Carrera carrera = Carrera.fromString(linea);
                    if (carrera != null) lista.add(carrera);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardar(Carrera carrera) {
        List<Carrera> lista = listar();
        for (Carrera c : lista) {
            if (c.getCodigo().equals(carrera.getCodigo())) {
                return actualizar(carrera);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(carrera.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Carrera carreraNueva) {
        List<Carrera> lista = listar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(carreraNueva.getCodigo())) {
                lista.set(i, carreraNueva);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Carrera c : lista) {
                pw.println(c.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        List<Carrera> lista = listar();
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
            for (Carrera c : lista) {
                pw.println(c.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Carrera buscarPorCodigo(String codigo) {
        List<Carrera> lista = listar();
        for (Carrera c : lista) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }
}
