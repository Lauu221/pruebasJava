package entrega2Test;

import etrega2Tipos.Cola;

public class TestCola {
	public static void main(String[] args) {
		
		Cola<String> cola = Cola.of();
		
		System.out.println("\n___Creamos una lista____");
		System.out.println("\nAñadiendo los elementos: 'primero', 'segundo', 'tercero'");
		cola.add("primero");
		cola.add("segundo");
		cola.add("tercero");
		System.out.println("\nElementos en la cola: " + cola);
		System.out.println("\nTamaño de la cola: " + cola.size());
		
		System.out.println("\n____Desencolando elementos____");
		
		while (!cola.isEmpty()) {
		    System.out.println("\nDesencolando: " + cola.poll());
		    System.out.println("\nCola restante: " + cola);
		}

	
		System.out.println("\n¿Está vacía? " + cola.isEmpty());

		try {
		    cola.poll();
		} catch (IllegalStateException e) {
		    System.out.println("\nExcepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
		}
		
	}

}
