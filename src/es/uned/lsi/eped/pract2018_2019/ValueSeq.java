package es.uned.lsi.eped.pract2018_2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ValueSeq extends Value {

	
	private ArrayList<Integer> secDigitos = new ArrayList<Integer>();
	
	/* Constructor: recibe un String con el valor numerico */
	public ValueSeq(String s) {
		
		for(char valor : s.toCharArray()){
			
			Integer n = Character.getNumericValue(valor);
			secDigitos.add(n);
		  }
	}
	
	public ArrayList<Integer> getSecDigitos (){
	     return secDigitos;
	}
	
	public void setSecDigitos (ArrayList<Integer> secDigitos){
	    this.secDigitos = secDigitos;
	}
	
	
	/* Metodo que transforma el valor numerico en un String */
	public String toString() {
		
		String r = "";
		for (Integer digito : secDigitos) {
			r = r + digito.toString();
		}
		return r;
	}

	/* Metodo que modifica el valor numerico llamante, sumandole el valor numerico parametro */
	public void addValue(Value n) {
		
		ValueSeq pr = (ValueSeq) n;
		int suma = 0;
		int llevada = 0;
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		Par par = new Par(pr.getSecDigitos(), this.getSecDigitos());
		par = igualarNumeroDigitos(par);	
		int cont = par.getP1().size();
		for (int i=cont-1;i>=0;i--) {
			suma = par.getP1().get(i) + par.getP2().get(i) + llevada;
			if (suma < 10) {
				resultado.add(suma);
				llevada = 0;
			} else {
				resultado.add(suma - 10);
				llevada = 1;
			}
		}
		if (llevada > 0) {
			resultado.add(llevada);
		}
		Collections.reverse(resultado);
		resultado = eliminarCeros(resultado);
		setSecDigitos(resultado);
	}
		
	private ArrayList<Integer>  cubrirConCeros(ArrayList<Integer> v, Integer z) {
		ArrayList<Integer> inicio = new ArrayList<Integer>();
		for (int i=0; i<z;i++) {
			inicio.add(0);
		}
		inicio.addAll(v);
		return inicio;
	}
	
	private Par igualarNumeroDigitos(Par p) {		
		int c1 = p.getP1().size();
		int c2 = p.getP2().size();
		if (c1 < c2) {
			p.setP1(cubrirConCeros(p.getP1(), c2 - c1));
		} else {
			p.setP2(cubrirConCeros(p.getP2(), c1 - c2));
		}
		return p;
	}
	
	
	private ArrayList<Integer>  eliminarCeros(ArrayList<Integer> resultado) {
		
		ArrayList<Integer> resLimpio = new ArrayList<Integer>();
		boolean primerN = false;
		for (int i = 0; i < resultado.size() - 1; i++) {
	        if (!resultado.get(i).equals(0) || primerN) {
	        	resLimpio.add(resultado.get(i));
	        	primerN = true;
	        }
	    }
		resLimpio.add(resultado.get(resultado.size() - 1));
		return resLimpio;
	}

	/* Metodo que modifica el valor numerico llamante, restandole el valor numerico parametro */
	/* Sabemos que el mayor es el valor numerico llamante */
	public void subValue(Value n) {
		
		ValueSeq pr = (ValueSeq) n;
		int resta = 0;
		int llevada = 0;
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		Par par = new Par(this.getSecDigitos(), pr.getSecDigitos());
		par = igualarNumeroDigitos(par);	
		int cont = par.getP1().size();
		for (int i=cont-1;i>=0;i--) {
			
			if (par.getP1().get(i) >= par.getP2().get(i) + llevada) {
				resta = par.getP1().get(i) - par.getP2().get(i) - llevada;
				llevada = 0;
				resultado.add(resta);
			} else {
				resta = par.getP1().get(i) + 10 - par.getP2().get(i) - llevada;
				llevada = 1;
				resultado.add(resta);
			}
		}
		Collections.reverse(resultado);
		resultado = eliminarCeros(resultado);
		setSecDigitos(resultado);
	}

	/* Metodo que modifica el valor numerico llamante, restandolo del valor numerico parametro */
	/* Sabemos que el mayor es el valor numerico parametro */
	public void subFromValue(Value n) {
		
		ValueSeq pr = (ValueSeq) n;
		int resta = 0;
		int llevada = 0;
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		Par par = new Par(this.getSecDigitos(), pr.getSecDigitos());
		par = igualarNumeroDigitos(par);	
		int cont = par.getP1().size();
		for (int i=cont-1;i>=0;i--) {
			
			if (par.getP2().get(i) >= par.getP1().get(i) + llevada) {
				resta = par.getP2().get(i) - par.getP1().get(i) - llevada;
				llevada = 0;
				resultado.add(resta);
			} else {
				resta = par.getP2().get(i) + 10 - par.getP1().get(i) - llevada;
				llevada = 1;
				resultado.add(resta);
			}
		}
		Collections.reverse(resultado);
		resultado = eliminarCeros(resultado);
		setSecDigitos(resultado);
	}

	/* Metodo que modifica el valor numerico llamante, multiplicandolo por el valor numerico parametro */
    public void multValue(Value n) {
    	
    	ValueSeq pr = (ValueSeq) n;
    	ArrayList<Integer> m1 = pr.getSecDigitos();
    	ArrayList<Integer> m2 = secDigitos;
    	ArrayList<Integer> resultado = new ArrayList<Integer>();
    	HashMap<Integer, Integer> operaciones = new HashMap<Integer, Integer>();
    	Integer s1 = m1.size(); 
    	Integer s2 = m2.size();
    	Integer tamanno = s1 + s2 + 1;
    
    	// iniciacion
    	for (int i=1; i<=tamanno; i++) {
    		operaciones.put(i, 0);
    	}
    
    	// aplicamos el algoritmo ABN de multiplicacion
    	Integer aux = 0;
    	Integer elemento = 0;
    	for (int i=0; i<s1; i++) {
    		for (int j=0; j<s2; j++) {
    			elemento = s1 - i + s2 - j;
    			aux = operaciones.get(elemento);
    			aux = aux + (m1.get(i) * m2.get(j));
    			operaciones.put(elemento, aux);
    		}  
    	}
    
    	// acarreo de los elementos
    	Integer resto = 0;
    	Integer decenas = 0;
    	for (int i=1; i<tamanno;i++) {
    		aux = operaciones.get(i);
    		if (aux > 9) {
    			decenas = aux / 10;
    			resto = aux % 10;
    			operaciones.put(i, resto);
    			operaciones.put(i+1, operaciones.get(i+1) + decenas);
    		}
    	}
    
    	// pasar el resultado a array
    	for (int i=2; i<=tamanno;i++) {
    		resultado.add(operaciones.get(i));
    	}
    
    	Collections.reverse(resultado);
    	resultado = eliminarCeros(resultado);
		setSecDigitos(resultado);
    }
	
	/* Metodo que indica si el valor numerico llamante es mayor que el valor numerico parametro */
    public boolean greater(Value n) {
		
		ValueSeq pr = (ValueSeq) n;
		Par par = new Par(this.getSecDigitos(), pr.getSecDigitos());
		par = igualarNumeroDigitos(par);	
		for (int i=0;i<par.getP2().size();i++) {
			if (par.getP1().get(i) > par.getP2().get(i)) {
				return true;
			} else if (par.getP1().get(i) < par.getP2().get(i)) {
				return false;
			}
		}
		return false;
	}

	/* Metodo que indica si el valor numerico es cero */
	public boolean isZero() {		
		for (int i=0;i<this.getSecDigitos().size();i++) {
			if (this.getSecDigitos().get(i) != 0) {
				return false;
			}
		}
		return true;
	}
	
}
