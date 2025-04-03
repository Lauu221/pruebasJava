
package entrega2Test;

import etrega2Tipos.ListaOrdenadaSinRepeticion;
import java.util.Arrays;
import java.util.List;


public class TestListaOrdenadaSinRepeticion {
	public static void main(String[] args) {
		
		ListaOrdenadaSinRepeticion<Integer> lista = ListaOrdenadaSinRepeticion.of(Integer::compareTo);
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 2, 5);
		
		System.out.println("\n____Creamos una lista____");
		System.out.println("\nAñado los elementos:" + numeros);
		lista.addAll(numeros);
		System.out.println("\nElementos en la lista: " + lista.elements());
		System.out.println("Hemos quitado los numeros repetidos");
		System.out.println("\nTamaño de la lista: " + lista.size());
		
		System.out.println("\n____Eliminamos un elemento____");
		System.out.println("\nEliminamos el primer elemento: " + lista.remove());
		System.out.println("\nElementos después de eliminar: " + lista.elements());
		
		System.out.println("\n____Añadimos elementos al lote____");
		System.out.println("\nAñadimos elementos en lote: 8, 5, 7");
		lista.addAll(Arrays.asList(8, 5, 7));
		System.out.println("\nElementos después de añadir lote: " + lista.elements());
	}
		}

