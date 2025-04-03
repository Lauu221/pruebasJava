package entrega2Test;

import etrega2Tipos.Pila;

public class TestPila {
	public static void main(String[] args) {
		Pila<Double> pila = new Pila<>();
		
		pila.add(1.1);
		pila.add(2.2);
		pila.add(3.3);
		
		System.out.println("\n___Creamos una Pila___");
		System.out.println("\nAñadimos los elementos: 1.1, 2.2, 3.3");
		System.out.println("\nElementos en la pila: " + pila);
		System.out.println("\nTamaño de la pila: " + pila.size());
		
		System.out.println("\nElemento en el top: " + pila.top());
		
		System.out.println("\n____Desapilando elementos:_____");
		while (!pila.isEmpty()) {
			System.out.println("\nDesapilando: " + pila.pop());
			System.out.println("\nPila restante: " + pila);			
			
		}
		System.out.println("\n¿Está vacía? " + pila.isEmpty());
		
		try {
			pila.top();
			
		} catch (IllegalStateException e) {
			System.out.println("\nExcepción correctamente lanzada al intentar acceder al tope de una pila vacía: " + e.getMessage());
					
		}
			
	}

}
