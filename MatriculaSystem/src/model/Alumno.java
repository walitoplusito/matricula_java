package model;

import java.io.Serializable;

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String dni;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String fechaNacimiento;
    private String direccionDistrito;
    private String direccionProvincia;
    private String direccionDepartamento;
    private String direccionPais;
    private String email;
    private String telefono;

    public Alumno() {}

    public Alumno(String codigo, String dni, String nombre1, String nombre2, 
                  String apellido1, String apellido2, String fechaNacimiento,
                  String direccionDistrito, String direccionProvincia, 
                  String direccionDepartamento, String direccionPais,
                  String email, String telefono) {
        this.codigo = codigo;
        this.dni = dni;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionDistrito = direccionDistrito;
        this.direccionProvincia = direccionProvincia;
        this.direccionDepartamento = direccionDepartamento;
        this.direccionPais = direccionPais;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    
    public String getNombre1() { return nombre1; }
    public void setNombre1(String nombre1) { this.nombre1 = nombre1; }
    
    public String getNombre2() { return nombre2; }
    public void setNombre2(String nombre2) { this.nombre2 = nombre2; }
    
    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
    
    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
    
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getDireccionDistrito() { return direccionDistrito; }
    public void setDireccionDistrito(String direccionDistrito) { this.direccionDistrito = direccionDistrito; }
    
    public String getDireccionProvincia() { return direccionProvincia; }
    public void setDireccionProvincia(String direccionProvincia) { this.direccionProvincia = direccionProvincia; }
    
    public String getDireccionDepartamento() { return direccionDepartamento; }
    public void setDireccionDepartamento(String direccionDepartamento) { this.direccionDepartamento = direccionDepartamento; }
    
    public String getDireccionPais() { return direccionPais; }
    public void setDireccionPais(String direccionPais) { this.direccionPais = direccionPais; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return codigo + "|" + dni + "|" + nombre1 + "|" + nombre2 + "|" + 
               apellido1 + "|" + apellido2 + "|" + fechaNacimiento + "|" + 
               direccionDistrito + "|" + direccionProvincia + "|" + 
               direccionDepartamento + "|" + direccionPais + "|" + 
               email + "|" + telefono;
    }

    public static Alumno fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 13) return null;
        return new Alumno(
            partes[0], partes[1], partes[2], partes[3],
            partes[4], partes[5], partes[6],
            partes[7], partes[8], partes[9], partes[10],
            partes[11], partes[12]
        );
    }
}
