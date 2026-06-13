package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Retiro;

public class RetiroDAO {
    private static final String ARCHIVO = "data/retiros.txt";

    public List<Retiro> listar() {
        List<Retiro> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Retiro retiro = Retiro.fromString(linea);
                    if (retiro != null) lista.add(retiro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardar(Retiro retiro) {
        List<Retiro> lista = listar();
        for (Retiro r : lista) {
            if (r.getCodigoRetiro().equals(retiro.getCodigoRetiro())) {
                return actualizar(retiro);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(retiro.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Retiro retiroNuevo) {
        List<Retiro> lista = listar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoRetiro().equals(retiroNuevo.getCodigoRetiro())) {
                lista.set(i, retiroNuevo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Retiro r : lista) {
                pw.println(r.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        List<Retiro> lista = listar();
        boolean eliminado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoRetiro().equals(codigo)) {
                lista.remove(i);
                eliminado = true;
                break;
            }
        }
        if (!eliminado) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Retiro r : lista) {
                pw.println(r.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Retiro buscarPorCodigo(String codigo) {
        List<Retiro> lista = listar();
        for (Retiro r : lista) {
            if (r.getCodigoRetiro().equals(codigo)) {
                return r;
            }
        }
        return null;
    }
}
