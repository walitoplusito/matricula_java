package model;

import java.io.Serializable;

public class Retiro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigoRetiro;
    private String codigoMatricula;
    private String fechaRetiro;
    private String motivo;

    public Retiro() {}

    public Retiro(String codigoRetiro, String codigoMatricula, String fechaRetiro, String motivo) {
        this.codigoRetiro = codigoRetiro;
        this.codigoMatricula = codigoMatricula;
        this.fechaRetiro = fechaRetiro;
        this.motivo = motivo;
    }

    // Getters y Setters
    public String getCodigoRetiro() { return codigoRetiro; }
    public void setCodigoRetiro(String codigoRetiro) { this.codigoRetiro = codigoRetiro; }
    
    public String getCodigoMatricula() { return codigoMatricula; }
    public void setCodigoMatricula(String codigoMatricula) { this.codigoMatricula = codigoMatricula; }
    
    public String getFechaRetiro() { return fechaRetiro; }
    public void setFechaRetiro(String fechaRetiro) { this.fechaRetiro = fechaRetiro; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public String toString() {
        return codigoRetiro + "|" + codigoMatricula + "|" + fechaRetiro + "|" + motivo;
    }

    public static Retiro fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length < 4) return null;
        return new Retiro(partes[0], partes[1], partes[2], partes[3]);
    }
}
