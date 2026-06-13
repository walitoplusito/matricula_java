package util;

import java.util.List;

public class ArchivoUtil {
    
    public static String generarCorrelativo(int maxActual, int longitudBase) {
        return String.format("%0" + longitudBase + "d", maxActual + 1);
    }
    
    public static String obtenerUltimoCorrelativo(List<String> codigos) {
        if (codigos.isEmpty()) return "0";
        String ultimo = codigos.get(codigos.size() - 1);
        return ultimo.replaceAll("\\D+", "");
    }
}
