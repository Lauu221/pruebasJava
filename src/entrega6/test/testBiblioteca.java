package entrega6.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entrega6.preguntas.preguntasBiblioteca;
import entrega6.preguntas.preguntasBiblioteca.Libro;

public class testBiblioteca {
	public static void main(String [] args) throws IOException{
		// a 
		List<preguntasBiblioteca.Libro> libros = preguntasBiblioteca.cargarLibros("biblioteca/libros.txt");
		List<preguntasBiblioteca.Prestamo> prestamos = preguntasBiblioteca.cargarPrestamos("biblioteca/prestamos.txt");
		
		Map.Entry<preguntasBiblioteca.Libro, Long> masPrestadofuncional = preguntasBiblioteca.masVecesPrestadoFuncional(libros, prestamos);
	System.out.println("_______Act a _______");
		if (masPrestadofuncional != null) {
			System.out.println("\n---- FUNCIONAL ----");
			System.out.println("libro más prestado: " + masPrestadofuncional.getKey().getTitulo());
			System.out.println("veces prestado: " + masPrestadofuncional.getValue());
				
		}else {
			System.out.println("No se encontró el libro más prestado.");
        
		}
	Map.Entry<preguntasBiblioteca.Libro, Long> masPrestadoImperativo = preguntasBiblioteca.masVecesPrestadoImperativo(libros, prestamos);
	if(masPrestadoImperativo != null) {
		System.out.println("\n---- IMPERATIVO ----");
		System.out.println("libro más prestado: " + masPrestadoImperativo.getKey().getTitulo());
		System.out.println("veces prestado: " + masPrestadoImperativo.getValue());
	}else {
		System.out.println("No se encontró el libro más prestado.");
	}
	
	
	//b
	System.out.println("\n_______Act b _______");
	String rutaLibros = "biblioteca/libros.txt";
	
	 try {
       System.out.println("\n---- IMPERATIVO ----");
         List<Libro> Libros = preguntasBiblioteca.cargarLibros(rutaLibros);
         List<String> autoresFiltrados = List.of("Leoncio Esteban Casanovas Méndez", "Mariano Uriarte Mínguez");

         Map<String, Set<String>> resultadoFiltrado = preguntasBiblioteca.librosPorAutorImperativo(libros, autoresFiltrados);

         
         System.out.println("Libros por autor:");
         resultadoFiltrado.forEach((autor, titulos) -> {
             System.out.println(autor + ": " + titulos);
         });

     } catch (IOException e) {
         System.err.println("Error al leer el archivo: " + e.getMessage());
     }
	 
	 System.out.println("\n---- FUNCIONAL ----");
	 List<Libro> librosFiltrados = preguntasBiblioteca.cargarLibros(rutaLibros);
	 List<String> autoresFiltradosFuncional = List.of("Leoncio Esteban Casanovas Méndez", "María Cristina de Amor");
	 Map<String, Set<String>> resultadoFiltradoFuncional = preguntasBiblioteca.librosPorAutorFuncional(libros, autoresFiltradosFuncional);
	 System.out.println("Libros por autor:");
		resultadoFiltradoFuncional.forEach((autor, titulos) -> {
			System.out.println(autor + ": " + titulos);
		});
 }
    
	
  }
	


