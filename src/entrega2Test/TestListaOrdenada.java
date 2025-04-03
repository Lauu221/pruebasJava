package entrega2Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import etrega2Tipos.ListaOrdenada;

public class TestListaOrdenada { 
	
	public static void main(String[] args) {
		PruebaConNumeros();
		PruebaConStrings();
	}
	
	
	private static void PruebaConNumeros(){
		
		System.out.println("\n___PRUEBA CON NÚMEROS___");
		ListaOrdenada<Integer> lista = ListaOrdenada.of(Integer::compareTo);
		
        lista.add(1);
        lista.add(5);
        lista.add(3);
        lista.add(4);
        lista.add(2);
        
        System.out.println("\n_____creamos una lista_______");
        System.out.println("\nAñadiendo elementos: 1, 5, 3, 4, 2");
        System.out.println("\nElementos en la lista: " + lista.elements());
        System.out.println("\nTamaño de la lista: " + lista.size());

        System.out.println("\n_____eliminamos el primer elemento_____");
        System.out.println("\nEliminando el primer elemento: " + lista.remove());
        System.out.println("\nElementos después de eliminar: " + lista.elements());

        System.out.println("\n_____añadimos elementos al lote_______");
        System.out.println("\nAñadiendo elementos en lote: 8, 6, 7");
        lista.addAll(Arrays.asList(8, 6, 7));
        System.out.println("\nElementos después de añadir lote: " + lista.elements());

        System.out.println("\n_____eliminamos todos los elementos_______");
        System.out.println("\nEliminando todos los elementos: " + lista.removeAll());
        System.out.println("\n¿Está vacía? " + lista.isEmpty());
        System.out.println("\nElementos después de eliminar todos: " + lista.elements());
    
	
      
	}
	
private static void PruebaConStrings(){
		System.out.println("\n___PRUEBA CON STRINGS___");
		ListaOrdenada<String> lista = ListaOrdenada.of(String::compareTo);
		List<String> palabrasIniciales = Arrays.asList("perro", "gato", "elefante", "loro", "tortuga");
		
		System.out.println("\nAñado elementos a la lista" + palabrasIniciales);
		lista.addAll(palabrasIniciales);
		System.out.println("\nElementos en la lista: " + lista.elements());
		
}
	}


	
	

