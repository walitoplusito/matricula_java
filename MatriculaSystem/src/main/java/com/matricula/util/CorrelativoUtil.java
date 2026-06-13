package com.matricula.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilidad para generación de correlativos.
 * 
 * @author Sistema de Matrícula
 * @version 1.0
 */
public class CorrelativoUtil {
    
    /**
     * Genera código de alumno con formato: AAAA + 5 dígitos
     * Ejemplo: 202610001
     * 
     * @param contador Número secuencial del alumno
     * @return Código generado
     */
    public static String generarCodigoAlumno(int contador) {
        int anio = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        return String.format("%d%05d", anio, contador);
    }
    
    /**
     * Genera código de matrícula con formato: 6 dígitos iniciando en 100001
     * 
     * @param contador Número secuencial de la matrícula
     * @return Código generado (100001 + contador - 1)
     */
    public static String generarCodigoMatricula(int contador) {
        return String.format("%06d", 100000 + contador);
    }
    
    /**
     * Genera código de retiro con formato: 6 dígitos iniciando en 200001
     * 
     * @param contador Número secuencial del retiro
     * @return Código generado (200000 + contador)
     */
    public static String generarCodigoRetiro(int contador) {
        return String.format("%06d", 200000 + contador);
    }
    
    /**
     * Extrae el número secuencial de un código de alumno.
     * 
     * @param codigo Código del alumno
     * @return Número secuencial
     */
    public static int obtenerSecuenciaAlumno(String codigo) {
        if (codigo != null && codigo.length() >= 9) {
            try {
                return Integer.parseInt(codigo.substring(4));
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
