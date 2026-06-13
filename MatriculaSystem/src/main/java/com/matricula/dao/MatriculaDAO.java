package com.matricula.dao;

import com.matricula.model.Matricula;
import com.matricula.util.ArchivoUtil;
import com.matricula.util.CorrelativoUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestión de Matrículas en archivos TXT.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class MatriculaDAO {
    
    private static final String ARCHIVO_MATRICULAS = "matriculas.txt";
    
    /**
     * Guarda una nueva matrícula en el archivo.
     * 
     * @param matricula Matrícula a guardar
     * @return true si se guardó correctamente
     */
    public boolean guardar(Matricula matricula) {
        // Verificar que no exista el código
        if (existePorCodigo(matricula.getCodigo())) {
            return false;
        }
        
        ArchivoUtil.escribirLinea(ARCHIVO_MATRICULAS, matricula.toString());
        return true;
    }
    
    /**
     * Obtiene todas las matrículas del archivo.
     * 
     * @return Lista de matrículas
     */
    public List<Matricula> listarTodos() {
        List<Matricula> matriculas = new ArrayList<>();
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_MATRICULAS);
        
        for (String linea : lineas) {
            Matricula matricula = Matricula.fromLine(linea);
            if (matricula != null) {
                matriculas.add(matricula);
            }
        }
        
        return matriculas;
    }
    
    /**
     * Busca una matrícula por su código.
     * 
     * @param codigo Código de la matrícula
     * @return Matrícula encontrada o null si no existe
     */
    public Matricula buscarPorCodigo(String codigo) {
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_MATRICULAS);
        
        for (String linea : lineas) {
            Matricula matricula = Matricula.fromLine(linea);
            if (matricula != null && matricula.getCodigo().equals(codigo)) {
                return matricula;
            }
        }
        
        return null;
    }
    
    /**
     * Busca matrículas por código de alumno.
     * 
     * @param codigoAlumno Código del alumno
     * @return Lista de matrículas del alumno
     */
    public List<Matricula> buscarPorAlumno(String codigoAlumno) {
        List<Matricula> matriculasEncontradas = new ArrayList<>();
        List<Matricula> todos = listarTodos();
        
        for (Matricula matricula : todos) {
            if (matricula.getCodigoAlumno().equals(codigoAlumno)) {
                matriculasEncontradas.add(matricula);
            }
        }
        
        return matriculasEncontradas;
    }
    
    /**
     * Busca matrículas por código de curso.
     * 
     * @param codigoCurso Código del curso
     * @return Lista de matrículas del curso
     */
    public List<Matricula> buscarPorCurso(String codigoCurso) {
        List<Matricula> matriculasEncontradas = new ArrayList<>();
        List<Matricula> todos = listarTodos();
        
        for (Matricula matricula : todos) {
            if (matricula.getCodigoCurso().equals(codigoCurso)) {
                matriculasEncontradas.add(matricula);
            }
        }
        
        return matriculasEncontradas;
    }
    
    /**
     * Busca una matrícula activa por alumno y curso.
     * 
     * @param codigoAlumno Código del alumno
     * @param codigoCurso Código del curso
     * @return Matrícula encontrada o null
     */
    public Matricula buscarPorAlumnoYCurso(String codigoAlumno, String codigoCurso) {
        List<Matricula> todos = listarTodos();
        
        for (Matricula matricula : todos) {
            if (matricula.getCodigoAlumno().equals(codigoAlumno) && 
                matricula.getCodigoCurso().equals(codigoCurso) &&
                matricula.getEstado().equals(Matricula.ESTADO_ACTIVA)) {
                return matricula;
            }
        }
        
        return null;
    }
    
    /**
     * Actualiza el estado de una matrícula.
     * 
     * @param matricula Matrícula con datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(Matricula matricula) {
        if (!existePorCodigo(matricula.getCodigo())) {
            return false;
        }
        
        List<String> lineas = ArchivoUtil.leerLineas(ARCHIVO_MATRICULAS);
        List<String> nuevasLineas = new ArrayList<>();
        
        for (String linea : lineas) {
            Matricula m = Matricula.fromLine(linea);
            if (m != null && m.getCodigo().equals(matricula.getCodigo())) {
                nuevasLineas.add(matricula.toString());
            } else {
                nuevasLineas.add(linea);
            }
        }
        
        ArchivoUtil.sobreescribirArchivo(ARCHIVO_MATRICULAS, nuevasLineas);
        return true;
    }
    
    /**
     * Verifica si existe una matrícula con el código dado.
     * 
     * @param codigo Código a verificar
     * @return true si existe
     */
    public boolean existePorCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }
    
    /**
     * Obtiene el siguiente correlativo disponible para matrícula.
     * 
     * @return Nuevo código de matrícula
     */
    public String obtenerNuevoCodigo() {
        List<Matricula> matriculas = listarTodos();
        int maxSecuencia = 0;
        
        for (Matricula matricula : matriculas) {
            try {
                int codigoNum = Integer.parseInt(matricula.getCodigo());
                int secuencia = codigoNum - 100000;
                if (secuencia > maxSecuencia) {
                    maxSecuencia = secuencia;
                }
            } catch (NumberFormatException e) {
                // Ignorar códigos inválidos
            }
        }
        
        return CorrelativoUtil.generarCodigoMatricula(maxSecuencia + 1);
    }
    
    /**
     * Cuenta el número total de matrículas.
     * 
     * @return Número de matrículas
     */
    public int contar() {
        return listarTodos().size();
    }
    
    /**
     * Cuenta matrículas activas por curso.
     * 
     * @param codigoCurso Código del curso
     * @return Número de matrículas activas
     */
    public int contarMatriculasActivasPorCurso(String codigoCurso) {
        int contador = 0;
        List<Matricula> todos = listarTodos();
        
        for (Matricula matricula : todos) {
            if (matricula.getCodigoCurso().equals(codigoCurso) && 
                matricula.getEstado().equals(Matricula.ESTADO_ACTIVA)) {
                contador++;
            }
        }
        
        return contador;
    }
}
