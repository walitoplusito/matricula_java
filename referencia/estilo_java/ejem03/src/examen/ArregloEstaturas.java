package examen;


public class ArregloEstaturas {
    private double Estatura[];
    private int indice;
    
    public ArregloEstaturas() {
        Estatura = new double[10];
        indice = 0;
    }

    public int tamanio() {
        return indice;
    }

    public double obtener(int p) {
        return Estatura[p];
    }

    public void adicionar(double n) {
        if (indice == tamanio())
            ampliarArreglo();
        Estatura[indice] = n;
        indice++;
    }

    private void ampliarArreglo() {
        double aux[] = Estatura;
        Estatura = new double[indice + 10];
        for (int i = 0; i < indice; i++)
            Estatura[i] = aux[i];
    }
    
    public void eliminarTodo() {
        indice = 0;
    }

    public void eliminarFinal() {
        indice--;
    }
    
    
    /*
    En la clase ArregloEstaturas:
    01. Un método denominado posicionUltimaEstaturaMenorQue170, que busque y retorne la posición de la  última Estatura menor que 170. En caso que no exista, retornar -1.
    02. Un método denominado sumaEstaturasMayoresQue170, que retorne la suma de todas las Estaturas mayores que 170.
    03. Un método denominado reemplazarUltimaEstaturaMenorQue170, que reemplace la última Estatura menor que 170, por la suma de las Estaturas mayores que 170. Si el reemplazo es posible, retorne true; en caso contrario, retorne false.
    04. Un método denominado incrementarEstaturasMenoresQue150 que incremente en 10 todas las Estaturas menores que 150 y retorne la cantidad de incrementos efectuados.
    05. Un método denominado eliminarUltimaEstaturaMenorQue150, que elimine la última Estatura menor que 150. Si la eliminación es posible, retorne true; en caso contrario, retorne false.
     */


    //===============================================
    //              CODIGO DEL ALUMNO
    //===============================================

    
    //01. Un método posicionUltimaEstaturaMenorQue170, busca y retorna ultima
    //Estatura menor que 170. En caso que no exista, retornar -1.
    public int posicionUltimaEstaturaMenorQue170() {
    	int pos=-1;    	     	
	    	for(int i=indice-1; i>=0; i--) {
	    		if (Estatura[i]<170) {
	    			pos=i;
	    			break;
	    		}
	    	}	    	
    	return pos;
    }
    
    
    //02. método sumaEstaturasMayoresQue170
    public int sumaEstaturasMayoresQue170() {
    	int suma=0;
    	int tam=tamanio();
    	for (int i=0; i<tam; i++)
    		if (Estatura[i]>170)
    			suma+=Estatura[i];
    	return suma;
    }
    
    
    //03. método reemplazarUltimaEstaturaMenorQue170, por sumaEstaturasMayoresQue170
    //Si el reemplazo es posible, retorne true; en caso contrario, retorne false.
    public boolean reemplazarUltimaEstaturaMenorQue170() {
    	int sum=sumaEstaturasMayoresQue170();
    	int pos=posicionUltimaEstaturaMenorQue170();
    	boolean posible=false;
    	if (pos!=-1 && sum>0) {
    		Estatura[pos]=sum;
    		posible=true;
    	}
    	return posible;
    }
    
    
    //04. Un método denominado incrementarEstaturasMenoresQue150
    //incrementa en 10 todas las Estaturas<150 y retorne nro incrementos efectuados.
    public int incrementarEstaturasMenoresQue150() {
    	int cont=0;
    	for (int i=0; i<tamanio(); i++)
    		if (Estatura[i]<150) {
    			Estatura[i]+=10;
    			cont++;
    		}
    	return cont;
    }
    
    
    //05. Un método denominado eliminarUltimaEstaturaMenorQue150
    //Si es posible, retorne true; si no, false.
    public boolean eliminarUltimaEstaturaMenorQue150() {
    	int posUltima=0;
    	boolean respuesta=false;
    	int tam=0;
    	
    	posUltima=posicionUltimaEstaturaMenorQue150();
    	tam=tamanio();
    	
    	if (posUltima!=-1){
    		for(int i=posUltima; i<tam-1; i++)
    			Estatura[i]=Estatura[i+1];
    		indice--;
    		respuesta=true;
    	}
    	return respuesta;    	
    }

    public int posicionUltimaEstaturaMenorQue150() {
    	int pos=-1;
    	int tam=tamanio();
    	if (tam!=0)     	
	    	for(int i=tam-1; i>=0; i--) {
	    		if (Estatura[i]<150) {
	    			pos=i;
	    			break;
	    		}	    			
	    	}    	
    	return pos;
    }

}



