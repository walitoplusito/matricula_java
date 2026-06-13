package model;

import java.io.Serializable;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nombre;
    private String creditos;
    private String modalidad; // Presencial, Semi-presencial, Virtual
    private String carreraCodigo;

    public Curso() {}

    public Curso(String codigo, String nombre, String creditos, String modalidad, String carreraCodigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.modalidad = modalidad;
        this.carreraCodigo = carreraCodigo;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCreditos() { return creditos; }
    public void setCreditos(String creditos) { this.creditos = creditos; }
    
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    
    public String getCarreraCodigo() { return carreraCodigo; }
    public void setCarreraCodigo(String carreraCodigo) { this.carreraCodigo = carreraCodigo; }

    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + creditos + "|" + modalidad + "|" + carreraCodigo;
    }

    public static Curso fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 5) return null;
        return new Curso(partes[0], partes[1], partes[2], partes[3], partes[4]);
    }
}
