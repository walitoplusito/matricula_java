/* Pregunta 2 (13 puntos)
Diseñe la clase Consultor en el paquete examen con los atributos privados: codigo (int), nombre (String), horas trabajadas (int) y tarifa por hora (double). 
-	Implemente, además: 
-	Una variable privada de clase que cuente la cantidad de objetos tipo Consultor creados (int). 
-	Una variable privada de clase que acumule los sueldos netos (double). 
-	Dos constantes públicas de clase para factores de descuentos por AFP y EPS (double). 
-	Un bloque de inicialización static para asignarle a la constante AFP el factor 0.10, a la constante EPS el factor 0.05 e inicializar con cero las variables privadas de clase. 
-	Un constructor que inicialice a todos los atributos, cuente la cantidad de objetos creados y acumule los sueldos netos. Haga uso de la referencia this. 
-	Un constructor con tres parámetros que inicialice sólo los atributos código, nombre y horas trabajadas, invocando al primer constructor usando la referencia this, enviando con el valor 65.0 la tarifa. 
-	Un constructor sin parámetros que invoque al segundo constructor usando la referencia this, enviando con 636 el código, con “Miguel” el nombre y con 30 las horas. 
-	Métodos de acceso público set para todos los atributos privados. Use la referencia this. 
-	Métodos de acceso público get para todos los atributos privados. 
-	Métodos públicos de clase set/get para las variables privadas de clase. 
-	Un método que retorne el sueldoBruto (horas*tarifa). 
-	Un método que retorne el descuentoAFP (aplicado al sueldo bruto). 
-	Un método que retorne el descuentoEPS (aplicado al sueldo bruto). 
-	Un método que retorne el sueldoNeto = (sueldoBruto – descuentoAFP – descuentoEPS). 
*/

package examen;

public class Consultor {
    // Atributos privados de instancia
    private int codigo;
    private String nombre;
    private int horasTrabajadas;
    private double tarifaPorHora;

    // Variables privadas de clase
    private static int cantidadConsultores = 0;
    private static double acumuladoSueldosNetos = 0.0;

    // Constantes públicas de clase
    public static final double AFP;
    public static final double EPS;
    
    // Bloque de inicialización estático
    static {
        AFP = 0.10;
        EPS = 0.05;
    
        cantidadConsultores = 0;
        acumuladoSueldosNetos = 0.0;
    }

    //inicializa atributos (constructor)
    public Consultor(int codigo, String nombre, int horasTrabajadas, double tarifaPorHora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
        // Contar y acumular sueldo neto
        cantidadConsultores++;
        acumuladoSueldosNetos += this.sueldoNeto();
    }

    // Constructor con tres parámetros (tarifa fija 65.0)
    public Consultor(int codigo, String nombre, int horasTrabajadas) {
        this(codigo, nombre, horasTrabajadas, 65.0);
    }

    // Constructor sin parámetros
    public Consultor() {
        this(2126, "Katu", 30);
    }

    // Métodos set (this)
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    // Métodos get
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    // set/get privadas de clase
    public static void setCantidadConsultores(int cantidad) {
        cantidadConsultores = cantidad;
    }

    public static int getCantidadConsultores() {
        return cantidadConsultores;
    }

    public static void setAcumuladoSueldosNetos(double acumulado) {
        acumuladoSueldosNetos = acumulado;
    }

    public static double getAcumuladoSueldosNetos() {
        return acumuladoSueldosNetos;
    }

    // sueldo bruto
    public double sueldoBruto() {
        return horasTrabajadas * tarifaPorHora;
    }

    // descuento AFP (aplicado al sueldo bruto)
    public double descuentoAFP() {
        return sueldoBruto() * AFP;
    }

    // descuento EPS (aplicado al sueldo bruto)
    public double descuentoEPS() {
        return sueldoBruto() * EPS;
    }

    // sueldo neto
    public double sueldoNeto() {
        return sueldoBruto() - descuentoAFP() - descuentoEPS();
    }
}