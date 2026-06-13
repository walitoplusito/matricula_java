package com.matricula.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un Retiro de matrícula de un curso.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class Retiro {
    
    private String codigo;
    private String codigoMatricula;
    private String codigoAlumno;
    private String codigoCurso;
    private String fechaRetiro;
    private String motivo;
    private String estado; // PROCESADO, PENDIENTE
    
    public static final String ESTADO_PROCESADO = "PROCESADO";
    public static final String ESTADO_PENDIENTE = "PENDIENTE";
    
    /**
     * Constructor por defecto.
     */
    public Retiro() {
        this.fechaRetiro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.estado = ESTADO_PROCESADO;
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param codigo Código único del retiro
     * @param codigoMatricula Código de la matrícula a retirar
     * @param codigoAlumno Código del alumno
     * @param codigoCurso Código del curso
     * @param motivo Motivo del retiro
     */
    public Retiro(String codigo, String codigoMatricula, String codigoAlumno, 
                  String codigoCurso, String motivo) {
        this();
        this.codigo = codigo;
        this.codigoMatricula = codigoMatricula;
        this.codigoAlumno = codigoAlumno;
        this.codigoCurso = codigoCurso;
        this.motivo = motivo;
    }
    
    // Getters y Setters
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigoMatricula() {
        return codigoMatricula;
    }
    
    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
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
    
    public String getFechaRetiro() {
        return fechaRetiro;
    }
    
    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Representación en texto del retiro para guardar en archivo.
     * Formato: codigo|codigoMatricula|codigoAlumno|codigoCurso|fechaRetiro|motivo|estado
     * 
     * @return Cadena con los datos del retiro separados por pipe
     */
    @Override
    public String toString() {
        return codigo + "|" + codigoMatricula + "|" + codigoAlumno + "|" + 
               codigoCurso + "|" + fechaRetiro + "|" + (motivo != null ? motivo : "") + "|" + estado;
    }
    
    /**
     * Crea un objeto Retiro desde una línea de archivo TXT.
     * 
     * @param linea Línea del archivo con formato pipe-separated
     * @return Objeto Retiro creado
     */
    public static Retiro fromLine(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 6) {
            return null;
        }
        
        Retiro retiro = new Retiro();
        retiro.setCodigo(partes[0]);
        retiro.setCodigoMatricula(partes[1]);
        retiro.setCodigoAlumno(partes[2]);
        retiro.setCodigoCurso(partes[3]);
        retiro.setFechaRetiro(partes[4]);
        retiro.setMotivo(partes[5]);
        
        if (partes.length > 6) {
            retiro.setEstado(partes[6]);
        }
        
        return retiro;
    }
}
