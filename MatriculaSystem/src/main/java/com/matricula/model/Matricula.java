package com.matricula.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa una Matrícula de un alumno a un curso.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class Matricula {
    
    private String codigo;
    private String codigoAlumno;
    private String codigoCurso;
    private String fechaMatricula;
    private String estado; // ACTIVA, RETIRADA
    private String observaciones;
    
    public static final String ESTADO_ACTIVA = "ACTIVA";
    public static final String ESTADO_RETIRADA = "RETIRADA";
    
    /**
     * Constructor por defecto.
     */
    public Matricula() {
        this.fechaMatricula = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.estado = ESTADO_ACTIVA;
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param codigo Código único de la matrícula
     * @param codigoAlumno Código del alumno matriculado
     * @param codigoCurso Código del curso matriculado
     */
    public Matricula(String codigo, String codigoAlumno, String codigoCurso) {
        this();
        this.codigo = codigo;
        this.codigoAlumno = codigoAlumno;
        this.codigoCurso = codigoCurso;
    }
    
    // Getters y Setters
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigoAlumno() {
        return codigoAlumno;
    }
    
    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }
    
    public String getCodigoCurso() {
        return codigoCurso;
    }
    
    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    
    public String getFechaMatricula() {
        return fechaMatricula;
    }
    
    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    /**
     * Representación en texto de la matrícula para guardar en archivo.
     * Formato: codigo|codigoAlumno|codigoCurso|fechaMatricula|estado|observaciones
     * 
     * @return Cadena con los datos de la matrícula separados por pipe
     */
    @Override
    public String toString() {
        return codigo + "|" + codigoAlumno + "|" + codigoCurso + "|" + 
               fechaMatricula + "|" + estado + "|" + (observaciones != null ? observaciones : "");
    }
    
    /**
     * Crea un objeto Matricula desde una línea de archivo TXT.
     * 
     * @param linea Línea del archivo con formato pipe-separated
     * @return Objeto Matricula creado
     */
    public static Matricula fromLine(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 5) {
            return null;
        }
        
        Matricula matricula = new Matricula();
        matricula.setCodigo(partes[0]);
        matricula.setCodigoAlumno(partes[1]);
        matricula.setCodigoCurso(partes[2]);
        matricula.setFechaMatricula(partes[3]);
        matricula.setEstado(partes[4]);
        
        if (partes.length > 5 && !partes[5].isEmpty()) {
            matricula.setObservaciones(partes[5]);
        }
        
        return matricula;
    }
}
