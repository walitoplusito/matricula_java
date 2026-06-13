package com.matricula.model;

/**
 * Clase que representa a un Alumno del sistema académico.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class Alumno {
    
    private String codigo;
    private String nombres;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
    private String direccion;
    private boolean activo;
    
    /**
     * Constructor por defecto.
     */
    public Alumno() {
        this.activo = true;
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param codigo Código único del alumno
     * @param nombres Nombres del alumno
     * @param apellidos Apellidos del alumno
     * @param dni Documento nacional de identidad
     * @param email Correo electrónico
     * @param telefono Número de teléfono
     * @param direccion Dirección domiciliaria
     */
    public Alumno(String codigo, String nombres, String apellidos, String dni, 
                  String email, String telefono, String direccion) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = true;
    }
    
    // Getters y Setters
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * Retorna el nombre completo del alumno.
     * 
     * @return Nombre completo (apellidos + nombres)
     */
    public String getNombreCompleto() {
        return apellidos + ", " + nombres;
    }
    
    /**
     * Representación en texto del alumno para guardar en archivo.
     * Formato: codigo|nombres|apellidos|dni|email|telefono|direccion|activo
     * 
     * @return Cadena con los datos del alumno separados por pipe
     */
    @Override
    public String toString() {
        return codigo + "|" + nombres + "|" + apellidos + "|" + dni + "|" + 
               email + "|" + telefono + "|" + direccion + "|" + (activo ? "1" : "0");
    }
    
    /**
     * Crea un objeto Alumno desde una línea de archivo TXT.
     * 
     * @param linea Línea del archivo con formato pipe-separated
     * @return Objeto Alumno creado
     */
    public static Alumno fromLine(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 7) {
            return null;
        }
        
        Alumno alumno = new Alumno();
        alumno.setCodigo(partes[0]);
        alumno.setNombres(partes[1]);
        alumno.setApellidos(partes[2]);
        alumno.setDni(partes[3]);
        alumno.setEmail(partes[4]);
        alumno.setTelefono(partes[5]);
        alumno.setDireccion(partes[6]);
        
        if (partes.length > 7) {
            alumno.setActivo(partes[7].equals("1"));
        }
        
        return alumno;
    }
}
