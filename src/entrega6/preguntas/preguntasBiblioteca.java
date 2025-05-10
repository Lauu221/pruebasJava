package entrega6.preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class preguntasBiblioteca {
	
	public static class Libro{
		private String dniLibro;
		private String titulo;
		private String autor;
		
		public Libro(String dniLibro, String titulo, String autor) {
			this.dniLibro = dniLibro;
			this.titulo = titulo;
			this.autor = autor;
		}

		public String getDniLibro() {
			return dniLibro;
		}

		public String getTitulo() {
			return titulo;
		}

		public String getAutor() {
			return autor;
		}
	}
	
	public static class Prestamo{
		private String dniLibro;
		private int ejemplar;
	
	
	public Prestamo(String dniLibro, int ejemplar) {
        this.dniLibro = dniLibro;
        this.ejemplar = ejemplar;
    }
	public String getDniLibro() {
		return dniLibro;
    }
	public int getEjemplar() {
		return ejemplar;
    }
	}
	
	public static List<Libro> cargarLibros(String ruta) throws IOException{
		  return Files.lines(Paths.get(ruta))
		            .map(line -> {
		                String[] partes = line.split(",", 4);
		                if (partes.length < 3) return null; 
		                return new Libro(partes[0], partes[1], partes[2]);
		            })
		            .filter(Objects::nonNull) 
		            .collect(Collectors.toList());
		}
	
	public static List<Prestamo> cargarPrestamos(String ruta) throws IOException{
		return Files.lines(Paths.get(ruta))
				.map(line -> {
					String[] partes = line.split(",");
					return new Prestamo(partes[0], Integer.parseInt(partes[1]));
				})
				.collect(Collectors.toList());
	}
	
	// -----------Preguntas-----------
	
	// a
	
	public static Map.Entry<Libro, Long> masVecesPrestadoFuncional(List<Libro> libros, List<Prestamo> prestamos){
		Map<String, Libro> mapaLibros = libros.stream()
				.collect(Collectors.toMap(Libro::getDniLibro, libro -> libro ));
		return prestamos.stream()
				.collect(Collectors.groupingBy(p-> mapaLibros.get(p.getDniLibro()), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.orElse(null);
	}
	
	public static Map.Entry<Libro, Long> masVecesPrestadoImperativo(List<Libro> libros, List<Prestamo> prestamos) {
	   
	    Map<String, Libro> mapaLibros = new HashMap<>();
	    for (Libro libro : libros) {
	        mapaLibros.put(libro.getDniLibro(), libro);
	    }
	    Map<Libro, Long> conteoPrestamos = new HashMap<>();
	    for (Prestamo prestamo : prestamos) {
	        Libro libro = mapaLibros.get(prestamo.getDniLibro());
	        if (libro != null) {
	            conteoPrestamos.put(libro, conteoPrestamos.getOrDefault(libro, 0L) + 1);
	        }
	    }
	    Map.Entry<Libro, Long> masPrestado = null;
	    for (Map.Entry<Libro, Long> entry : conteoPrestamos.entrySet()) {
	        if (masPrestado == null || entry.getValue() > masPrestado.getValue()) {
	            masPrestado = entry;
	        }
	    }

	    return masPrestado;
	}
	// b
	public static Map<String, Set<String>> librosPorAutorFuncional(List<Libro> libros, List<String> nombres){
		return libros.stream()
				.filter(libro -> nombres == null || nombres.contains(libro.getAutor()))
				.collect(Collectors.groupingBy(Libro::getAutor, Collectors.mapping(Libro::getTitulo, Collectors.toSet())));
	}
	
	public static Map<String, Set<String>> librosPorAutorImperativo(List<Libro> libros, List<String> nombres) {
		Map<String, Set<String>> resultado = new HashMap<>();
	    for (Libro libro : libros) {
	        String autor = libro.getAutor();
	        if (autor == null) continue; 

	        if (nombres == null || nombres.contains(autor)) {
	            resultado.putIfAbsent(autor, new HashSet<>());
	            resultado.get(autor).add(libro.getTitulo());
	        }
	    }
	    return resultado;
	}

}


