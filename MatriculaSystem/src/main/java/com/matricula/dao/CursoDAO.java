package com.matricula.dao;

import com.matricula.model.Curso;
import com.matricula.util.ArchivoUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestión de Cursos en archivos TXT.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class CursoDAO {
    
    private static final String ARCHIVO_CURSOS = "cursos.txt";
    
    /**
     * Guarda un nuevo curso en el archivo.
     * 
     * @param curso Curso a guardar
     * @return true si se guardó correctamente
     */
    public boolean guardar(Curso curso) {
        // Verificar que no exista el código
        if (existePorCodigo(curso.getCodigo())) {
            return false;
        }
        
        ArchivoUtil.escribirLinea(ARCHIVO_CURSOS, curso.toString());
        return true;
    }
    
    /**
     * Obtiene todos los cursos del archivo.
     * 
     * @return Lista de cursos
     */
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_CURSOS);
        
        for (String linea : lineas) {
            Curso curso = Curso.fromLine(linea);
            if (curso != null) {
                cursos.add(curso);
            }
        }
        
        return cursos;
    }
    
    /**
     * Busca un curso por su código.
     * 
     * @param codigo Código del curso
     * @return Curso encontrado o null si no existe
     */
    public Curso buscarPorCodigo(String codigo) {
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_CURSOS);
        
        for (String linea : lineas) {
            Curso curso = Curso.fromLine(linea);
            if (curso != null && curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        
        return null;
    }
    
    /**
     * Busca cursos por nombre (búsqueda parcial).
     * 
     * @param nombre Nombre o parte del nombre del curso
     * @return Lista de cursos encontrados
     */
    public List<Curso> buscarPorNombre(String nombre) {
        List<Curso> cursosEncontrados = new ArrayList<>();
        List<Curso> todos = listarTodos();
        
        for (Curso curso : todos) {
            if (curso.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                cursosEncontrados.add(curso);
            }
        }
        
        return cursosEncontrados;
    }
    
    /**
     * Actualiza los datos de un curso existente.
     * 
     * @param curso Curso con datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(Curso curso) {
        if (!existePorCodigo(curso.getCodigo())) {
            return false;
        }
        
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_CURSOS);
        List<String> nuevasLineas = new ArrayList<>();
        
        for (String linea : lineas) {
            Curso c = Curso.fromLine(linea);
            if (c != null && c.getCodigo().equals(curso.getCodigo())) {
                nuevasLineas.add(curso.toString());
            } else {
                nuevasLineas.add(linea);
            }
        }
        
        ArchivoUtil.sobreescribirArchivo(ARCHIVO_CURSOS, nuevasLineas);
        return true;
    }
    
    /**
     * Elimina lógicamente un curso (lo marca como inactivo).
     * 
     * @param codigo Código del curso a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarLogico(String codigo) {
        Curso curso = buscarPorCodigo(codigo);
        if (curso == null) {
            return false;
        }
        
        curso.setActivo(false);
        return actualizar(curso);
    }
    
    /**
     * Verifica si existe un curso con el código dado.
     * 
     * @param codigo Código a verificar
     * @return true si existe
     */
    public boolean existePorCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }
    
    /**
     * Cuenta el número total de cursos.
     * 
     * @return Número de cursos
     */
    public int contar() {
        return listarTodos().size();
    }
    
    /**
     * Obtiene lista de cursos activos con vacantes disponibles.
     * 
     * @return Lista de cursos disponibles
     */
    public List<Curso> listarCursosDisponibles() {
        List<Curso> disponibles = new ArrayList<>();
        List<Curso> todos = listarTodos();
        
        for (Curso curso : todos) {
            if (curso.isActivo() && curso.hayVacantes()) {
                disponibles.add(curso);
            }
        }
        
        return disponibles;
    }
}
