package model;

import java.io.Serializable;

public class Carrera implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nombre;
    private String facultad;
    private String duracionAnios;

    public Carrera() {}

    public Carrera(String codigo, String nombre, String facultad, String duracionAnios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.facultad = facultad;
        this.duracionAnios = duracionAnios;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }
    
    public String getDuracionAnios() { return duracionAnios; }
    public void setDuracionAnios(String duracionAnios) { this.duracionAnios = duracionAnios; }

    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + facultad + "|" + duracionAnios;
    }

    public static Carrera fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 4) return null;
        return new Carrera(partes[0], partes[1], partes[2], partes[3]);
    }
}
