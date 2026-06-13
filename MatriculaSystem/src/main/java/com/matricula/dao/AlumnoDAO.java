package com.matricula.dao;

import com.matricula.model.Alumno;
import com.matricula.util.ArchivoUtil;
import com.matricula.util.CorrelativoUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestión de Alumnos en archivos TXT.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class AlumnoDAO {
    
    private static final String ARCHIVO_ALUMNOS = "alumnos.txt";
    
    /**
     * Guarda un nuevo alumno en el archivo.
     * 
     * @param alumno Alumno a guardar
     * @return true si se guardó correctamente
     */
    public boolean guardar(Alumno alumno) {
        // Verificar que no exista el código
        if (existePorCodigo(alumno.getCodigo())) {
            return false;
        }
        
        ArchivoUtil.escribirLinea(ARCHIVO_ALUMNOS, alumno.toString());
        return true;
    }
    
    /**
     * Obtiene todos los alumnos del archivo.
     * 
     * @return Lista de alumnos
     */
    public List<Alumno> listarTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_ALUMNOS);
        
        for (String linea : lineas) {
            Alumno alumno = Alumno.fromLine(linea);
            if (alumno != null) {
                alumnos.add(alumno);
            }
        }
        
        return alumnos;
    }
    
    /**
     * Busca un alumno por su código.
     * 
     * @param codigo Código del alumno
     * @return Alumno encontrado o null si no existe
     */
    public Alumno buscarPorCodigo(String codigo) {
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_ALUMNOS);
        
        for (String linea : lineas) {
            Alumno alumno = Alumno.fromLine(linea);
            if (alumno != null && alumno.getCodigo().equals(codigo)) {
                return alumno;
            }
        }
        
        return null;
    }
    
    /**
     * Busca alumnos por DNI.
     * 
     * @param dni DNI del alumno
     * @return Lista de alumnos encontrados
     */
    public List<Alumno> buscarPorDni(String dni) {
        List<Alumno> alumnosEncontrados = new ArrayList<>();
        List<Alumno> todos = listarTodos();
        
        for (Alumno alumno : todos) {
            if (alumno.getDni().equals(dni)) {
                alumnosEncontrados.add(alumno);
            }
        }
        
        return alumnosEncontrados;
    }
    
    /**
     * Actualiza los datos de un alumno existente.
     * 
     * @param alumno Alumno con datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(Alumno alumno) {
        if (!existePorCodigo(alumno.getCodigo())) {
            return false;
        }
        
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_ALUMNOS);
        List<String> nuevasLineas = new ArrayList<>();
        
        for (String linea : lineas) {
            Alumno a = Alumno.fromLine(linea);
            if (a != null && a.getCodigo().equals(alumno.getCodigo())) {
                nuevasLineas.add(alumno.toString());
            } else {
                nuevasLineas.add(linea);
            }
        }
        
        ArchivoUtil.sobreescribirArchivo(ARCHIVO_ALUMNOS, nuevasLineas);
        return true;
    }
    
    /**
     * Elimina lógicamente un alumno (lo marca como inactivo).
     * 
     * @param codigo Código del alumno a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarLogico(String codigo) {
        Alumno alumno = buscarPorCodigo(codigo);
        if (alumno == null) {
            return false;
        }
        
        alumno.setActivo(false);
        return actualizar(alumno);
    }
    
    /**
     * Verifica si existe un alumno con el código dado.
     * 
     * @param codigo Código a verificar
     * @return true si existe
     */
    public boolean existePorCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }
    
    /**
     * Obtiene el siguiente correlativo disponible para alumno.
     * 
     * @return Nuevo código de alumno
     */
    public String obtenerNuevoCodigo() {
        List<Alumno> alumnos = listarTodos();
        int maxSecuencia = 0;
        
        for (Alumno alumno : alumnos) {
            int secuencia = CorrelativoUtil.obtenerSecuenciaAlumno(alumno.getCodigo());
            if (secuencia > maxSecuencia) {
                maxSecuencia = secuencia;
            }
        }
        
        return CorrelativoUtil.generarCodigoAlumno(maxSecuencia + 1);
    }
    
    /**
     * Cuenta el número total de alumnos.
     * 
     * @return Número de alumnos
     */
    public int contar() {
        return listarTodos().size();
    }
}
