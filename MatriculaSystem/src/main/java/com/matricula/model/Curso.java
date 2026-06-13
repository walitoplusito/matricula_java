package com.matricula.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Curso académico.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class Curso {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int vacantes;
    private int vacantesOcupadas;
    private String profesor;
    private String horario;
    private boolean activo;
    private List<String> prerequisitos;
    
    /**
     * Constructor por defecto.
     */
    public Curso() {
        this.vacantesOcupadas = 0;
        this.activo = true;
        this.prerequisitos = new ArrayList<>();
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param codigo Código único del curso
     * @param nombre Nombre del curso
     * @param descripcion Descripción del curso
     * @param creditos Número de créditos
     * @param vacantes Número total de vacantes
     * @param profesor Nombre del profesor
     * @param horario Horario del curso
     */
    public Curso(String codigo, String nombre, String descripcion, int creditos,
                 int vacantes, String profesor, String horario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.vacantes = vacantes;
        this.profesor = profesor;
        this.horario = horario;
        this.vacantesOcupadas = 0;
        this.activo = true;
        this.prerequisitos = new ArrayList<>();
    }
    
    // Getters y Setters
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getCreditos() {
        return creditos;
    }
    
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    public int getVacantes() {
        return vacantes;
    }
    
    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }
    
    public int getVacantesOcupadas() {
        return vacantesOcupadas;
    }
    
    public void setVacantesOcupadas(int vacantesOcupadas) {
        this.vacantesOcupadas = vacantesOcupadas;
    }
    
    public String getProfesor() {
        return profesor;
    }
    
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public List<String> getPrerequisitos() {
        return prerequisitos;
    }
    
    public void setPrerequisitos(List<String> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }
    
    /**
     * Agrega un prerequisito al curso.
     * 
     * @param codigoPrereq Código del curso prerequisito
     */
    public void agregarPrerequisito(String codigoPrereq) {
        if (!prerequisitos.contains(codigoPrereq)) {
            prerequisitos.add(codigoPrereq);
        }
    }
    
    /**
     * Verifica si hay vacantes disponibles.
     * 
     * @return true si hay vacantes disponibles
     */
    public boolean hayVacantes() {
        return vacantesOcupadas < vacantes;
    }
    
    /**
     * Obtiene el número de vacantes disponibles.
     * 
     * @return Número de vacantes disponibles
     */
    public int getVacantesDisponibles() {
        return vacantes - vacantesOcupadas;
    }
    
    /**
     * Incrementa las vacantes ocupadas.
     * 
     * @return true si se pudo incrementar
     */
    public boolean ocuparVacante() {
        if (hayVacantes()) {
            vacantesOcupadas++;
            return true;
        }
        return false;
    }
    
    /**
     * Libera una vacante ocupada.
     */
    public void liberarVacante() {
        if (vacantesOcupadas > 0) {
            vacantesOcupadas--;
        }
    }
    
    /**
     * Representación en texto del curso para guardar en archivo.
     * Formato: codigo|nombre|descripcion|creditos|vacantes|vacantesOcupadas|profesor|horario|activo
     * 
     * @return Cadena con los datos del curso separados por pipe
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(codigo).append("|");
        sb.append(nombre).append("|");
        sb.append(descripcion).append("|");
        sb.append(creditos).append("|");
        sb.append(vacantes).append("|");
        sb.append(vacantesOcupadas).append("|");
        sb.append(profesor).append("|");
        sb.append(horario).append("|");
        sb.append(activo ? "1" : "0");
        
        // Agregar prerequisitos si existen
        if (!prerequisitos.isEmpty()) {
            sb.append("|");
            for (int i = 0; i < prerequisitos.size(); i++) {
                sb.append(prerequisitos.get(i));
                if (i < prerequisitos.size() - 1) {
                    sb.append(",");
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Crea un objeto Curso desde una línea de archivo TXT.
     * 
     * @param linea Línea del archivo con formato pipe-separated
     * @return Objeto Curso creado
     */
    public static Curso fromLine(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 8) {
            return null;
        }
        
        Curso curso = new Curso();
        curso.setCodigo(partes[0]);
        curso.setNombre(partes[1]);
        curso.setDescripcion(partes[2]);
        
        try {
            curso.setCreditos(Integer.parseInt(partes[3]));
            curso.setVacantes(Integer.parseInt(partes[4]));
            curso.setVacantesOcupadas(Integer.parseInt(partes[5]));
        } catch (NumberFormatException e) {
            curso.setCreditos(0);
            curso.setVacantes(0);
            curso.setVacantesOcupadas(0);
        }
        
        curso.setProfesor(partes[6]);
        curso.setHorario(partes[7]);
        
        if (partes.length > 8) {
            curso.setActivo(partes[8].equals("1"));
        }
        
        // Leer prerequisitos si existen
        if (partes.length > 9 && !partes[9].isEmpty()) {
            String[] prereqs = partes[9].split(",");
            for (String prereq : prereqs) {
                curso.agregarPrerequisito(prereq.trim());
            }
        }
        
        return curso;
    }
}
