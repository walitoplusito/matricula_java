package examen;

public class Lavadora {
    // Atributos privados
    private int codigo;
    private double ancho;
    private double alto;
    private double profundidad;

    // Constructor que inicializa todos los atributos
    public Lavadora(int codigo, double ancho, double alto, double profundidad) {
        this.codigo = codigo;
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
    }

    // Métodos de acceso set/get
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAlto() {
        return alto;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public double getProfundidad() {
        return profundidad;
    }

    // Método que retorna el área de la base (ancho * profundidad)
    public double areaBase() {
        return ancho * profundidad;
    }

    // Método que retorna el volumen (área de la base * alto)
    public double volumen() {
        return areaBase() * alto;
    }

    // Método que retorna el tipo de lavadora según la tabla
    public String getTipo() {
        double vol = volumen();
        if (vol < 1.0) {
            return "Pequeña";
        } else if (vol >= 1.2 && vol < 1.5) {
            return "Mediana";
        } else if (vol > 2.0) {
            return "Grande";
        } else {
            return "No definido"; // Rangos no cubiertos por la tabla
        }
    }

    // Método que retorna los datos procesados de lavadora
    public String getDatos() {
        return "Código: " + codigo + "\n" +
               "Ancho: " + ancho + "	" +
               "Alto: " + alto + "	" +
               "Profundidad: " + profundidad + "\n" +
               "Area base: " + areaBase() +"	" +
               "Volumen: " + volumen() +"\n" +
               "Tipo: " + getTipo() +"\n"; 
    }
}