package model;

import java.io.Serializable;

public class Matricula implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigoMatricula;
    private String alumnoCodigo;
    private String cursoCodigo;
    private String periodo;
    private String nota;

    public Matricula() {}

    public Matricula(String codigoMatricula, String alumnoCodigo, String cursoCodigo, 
                     String periodo, String nota) {
        this.codigoMatricula = codigoMatricula;
        this.alumnoCodigo = alumnoCodigo;
        this.cursoCodigo = cursoCodigo;
        this.periodo = periodo;
        this.nota = nota;
    }

    // Getters y Setters
    public String getCodigoMatricula() { return codigoMatricula; }
    public void setCodigoMatricula(String codigoMatricula) { this.codigoMatricula = codigoMatricula; }
    
    public String getAlumnoCodigo() { return alumnoCodigo; }
    public void setAlumnoCodigo(String alumnoCodigo) { this.alumnoCodigo = alumnoCodigo; }
    
    public String getCursoCodigo() { return cursoCodigo; }
    public void setCursoCodigo(String cursoCodigo) { this.cursoCodigo = cursoCodigo; }
    
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    
    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }

    @Override
    public String toString() {
        return codigoMatricula + "|" + alumnoCodigo + "|" + cursoCodigo + "|" + periodo + "|" + nota;
    }

    public static Matricula fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 5) return null;
        return new Matricula(partes[0], partes[1], partes[2], partes[3], partes[4]);
    }
}
