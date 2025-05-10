package entrega6.preguntas;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

import javax.naming.spi.DirStateFactory.Result;

public class preguntasCentro {

	
 //    ________________CLASES DE DATOS_________________
    static class Profesor {
        String nombre;
		String dni;
        LocalDateTime nacimiento;

        public Profesor(String nombre, String dni, LocalDateTime nacimiento) {
        	this.nombre = nombre;
            this.dni = dni;
            this.nacimiento = nacimiento;
        }

        public int getEdad() {
            return Period.between(nacimiento.toLocalDate(), LocalDate.now()).getYears();
        }
        
    }
    
    public static class Alumno {
    	String nombre;
        String dni;
        LocalDate fechaNacimiento;
        double nota;
		

        public Alumno(String nombre, String dni, LocalDate fechaNacimiento, double nota) {
        	this.nombre = nombre;
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
            this.nota = nota;
            }
        public LocalDate getfechaNacimiento() {
			return fechaNacimiento;
		}
        public String getNombre() {
        	  return nombre;
        }

		public double getNota() {
			return nota;
		}
        
    }
    static class Asignacion {
        String dniProfesor;
        int idAsignatura;
        int idGrupo;

        public Asignacion(String dniProfesor, int idAsignatura, int idGrupo) {
            this.dniProfesor = dniProfesor;
            this.idAsignatura = idAsignatura;
            this.idGrupo = idGrupo;
        }
    }

    static class Matricula {
        String dniAlumno;
        int idAsignatura;
        int idGrupo;

        public Matricula(String dniAlumno, int idAsignatura, int idGrupo) {
            this.dniAlumno = dniAlumno;
            this.idAsignatura = idAsignatura;
            this.idGrupo = idGrupo;
        }
    }

 // _______________CARGA DE ARCHIVOS______________
	public static int calcularEdad(LocalDate fechaNacimiento) {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}
	
	public static List<Alumno> cargarAlumnos(String ruta) throws IOException {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Files.lines(Paths.get(ruta))
                .map(line -> line.split(","))
                .map(p -> new Alumno( p[0] + " " + p[1], 
                		p[2],
                		LocalDateTime.parse(p[3], formatter).toLocalDate(), 
                		Double.parseDouble(p[6])
                		))
                .toList();
	}
    
    
    public static List<Profesor> cargarProfesores(String ruta) throws IOException {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Files.lines(Paths.get(ruta))
                .map(line -> line.split(","))
                .map(p -> new Profesor(p[0]+ " " + p[1] ,p[2], LocalDateTime.parse(p[3], formatter)))
                .toList();
    }

    public static List<Asignacion> cargarAsignaciones(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta))
                .map(line -> line.split(","))
                .map(p -> new Asignacion(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2])))
                .toList();
    }

    public static List<Matricula> cargarMatriculas(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta))
                .map(line -> line.split(","))
                .map(p -> new Matricula(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2])))
                .toList();
    }

  //	-------------- ACTIVIDADES--------------
    // a
    public static double promedioEdadProfesoresImperativo(String dniAlumno) throws IOException {
        List<Profesor> profesores = cargarProfesores("centro/profesores.txt");
        List<Asignacion> asignaciones = cargarAsignaciones("centro/asignaciones.txt");
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");

        Set<String> profesoresAlumno = new HashSet<>();
        for (Matricula m : matriculas) {
            if (m.dniAlumno.equals(dniAlumno)) {
                for (Asignacion a : asignaciones) {
                    if (a.idAsignatura == m.idAsignatura && a.idGrupo == m.idGrupo) {
                        profesoresAlumno.add(a.dniProfesor);
                    }
                }
            }
        }

        int sumaEdad = 0;
        int contador = 0;
        for (Profesor p : profesores) {
            if (profesoresAlumno.contains(p.dni)) {
                sumaEdad += p.getEdad();
                contador++;
            }
        }

        return contador > 0 ? (double) sumaEdad / contador : 0.0;
    }


    public static double promedioEdadProfesoresFuncional(String dniAlumno) throws IOException {
        List<Profesor> profesores = cargarProfesores("centro/profesores.txt");
        List<Asignacion> asignaciones = cargarAsignaciones("centro/asignaciones.txt");
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");

        Set<String> profesoresAlumno = matriculas.stream()
                .filter(m -> m.dniAlumno.equals(dniAlumno))
                .flatMap(m -> asignaciones.stream()
                        .filter(a -> a.idAsignatura == m.idAsignatura && a.idGrupo == m.idGrupo)
                        .map(a -> a.dniProfesor))
                .collect(Collectors.toSet());

        return profesores.stream()
                .filter(p -> profesoresAlumno.contains(p.dni))
                .mapToInt(Profesor::getEdad)
                .average()
                .orElse(0.0);
    }
    // b
    
    public static int grupoMayorDiversidadEdadFuncional() throws IOException {
    	List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");

       
        Map<Integer, List<Integer>> grupoEdades = matriculas.stream()
                .collect(Collectors.groupingBy(m -> m.idGrupo,
                        Collectors.mapping(m -> alumnos.stream()
                                        .filter(a -> a.dni.equals(m.dniAlumno))
                                        .findFirst()
                                        .map(a -> calcularEdad(a.fechaNacimiento))
                                        .orElse(null),
                                Collectors.toList()
                        )
                ));

        return grupoEdades.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1 && !entry.getValue().contains(null))
                .max(Comparator.comparingInt(entry -> Collections.max(entry.getValue()) - Collections.min(entry.getValue())))
                .map(Map.Entry::getKey)
                .orElse(0);
    }

    
    
    public static int grupoMayorDiversidadEdadImperativo() throws IOException {
        List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");

        Map<Integer, List<LocalDate>> gruposFechasNacimiento = new HashMap<>();

        for (Matricula matricula : matriculas) {
            for (Alumno alumno : alumnos) {
                if (alumno.dni.equals(matricula.dniAlumno)) {
                    gruposFechasNacimiento.putIfAbsent(matricula.idGrupo, new ArrayList<>());
                    gruposFechasNacimiento.get(matricula.idGrupo).add(alumno.fechaNacimiento);
                    break;
                }
            }
        }

        int grupoMayorDiversidad = 0;
        int maxDiferenciaEdad = 0;

        for (Map.Entry<Integer, List<LocalDate>> entry : gruposFechasNacimiento.entrySet()) {
            List<LocalDate> fechasNacimientoGrupo = entry.getValue();

            if (fechasNacimientoGrupo.size() < 2) {
                continue; 
            }

            List<Integer> edades = fechasNacimientoGrupo.stream()
                    .map(preguntasCentro::calcularEdad)
                    .collect(Collectors.toList());

            int edadMaxima = Collections.max(edades);
            int edadMinima = Collections.min(edades);
            int diferenciaEdad = edadMaxima - edadMinima;

            if (diferenciaEdad > maxDiferenciaEdad) {
                maxDiferenciaEdad = diferenciaEdad;
                grupoMayorDiversidad = entry.getKey();
            }
        }

        return grupoMayorDiversidad;
    }
    
    // c
    public static String alumnoMasMatriculasFuncional() throws IOException {
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");
        List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");

        String dniMasMatriculas = matriculas.stream()
        		.collect(Collectors.groupingBy(m -> m.dniAlumno, Collectors.counting()))
        		.entrySet().stream()
        		.max(Map.Entry.comparingByValue())
        		.map(Map.Entry::getKey)
        		.orElse(null);
		if (dniMasMatriculas == null) {
			return null;
			}return alumnos.stream()
				.filter(a -> a.dni.equals(dniMasMatriculas))
				.map(a -> "DNI:"+ a.dni +" Nombre: " + a.nombre)
				.findFirst()
				.orElse("Alumno no encontrado");
		
		
    }
    public static String alumnoMasMatriculasImperativo() throws IOException {
        List<Matricula> matriculas = cargarMatriculas("centro/matriculas.txt");
        List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");

        Map<String, Integer> contador = new HashMap<>();
        for (Matricula m : matriculas) {
            contador.put(m.dniAlumno, contador.getOrDefault(m.dniAlumno, 0) + 1);
        }

        String dniMax = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                dniMax = entry.getKey();
            }
        }

        if (dniMax == null) return null;
        for (Alumno a : alumnos) {
            if (a.dni.equals(dniMax)) {
                return "DNI: " + a.dni + ", Nombre: " + a.nombre;
            }
        }

        return "Alumno no encontrado";
    }
    
   // d
  public static Map<String, List<String>> rangosEdadPorAlumnoFuncional(String cadenaRangos) throws IOException{
	  if (cadenaRangos == null || cadenaRangos.trim().isEmpty()) {
	        throw new IllegalArgumentException("La cadena de rangos no puede estar vacía.");
	    }
	  List<int[]> rangos = Arrays.stream(cadenaRangos.split(","))
		        .map(String::trim)
		        .map(r -> {String[] partes = r.split("-");
		            if (partes.length != 2) throw new IllegalArgumentException("Formato incorrecto: " + r);
		            int inicio = Integer.parseInt(partes[0].trim());
		            int fin = Integer.parseInt(partes[1].trim());
		            if (inicio > fin) throw new IllegalArgumentException("Rango inválido: " + r);
		            return new int[]{inicio, fin};
		        })
		        .sorted(Comparator.comparingInt(r -> r[0]))
		        .collect(Collectors.toList());

		    
		    for (int i = 1; i < rangos.size(); i++) {
		        if (rangos.get(i - 1)[1] >= rangos.get(i)[0]) {
		            throw new IllegalArgumentException("Los rangos no pueden solaparse.");
		        }
		    }

		    
		    List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
		    Map<String, List<String>> resultado = new LinkedHashMap<>();
		    rangos.forEach(r -> resultado.put(r[0] + "-" + r[1], new ArrayList<>()));
		   
		    alumnos.forEach(alumno -> {
		        int edad = calcularEdad(alumno.fechaNacimiento);
		        rangos.stream()
		            .filter(r -> edad >= r[0] && edad <= r[1])
		            .findFirst()
		            .ifPresent(r -> resultado.get(r[0] + "-" + r[1])
		                .add(alumno.nombre + " (" + edad + " años)"));
		    });

		    return resultado;
		}
  
  
  public static Map<String, List<String>> rangosEdadPorAlumnoImperativo(String cadenaRangos) throws IOException {
	    if (cadenaRangos == null || cadenaRangos.trim().isEmpty()) {
	        throw new IllegalArgumentException("La cadena de rangos no puede estar vacía.");
	    }

	    List<int[]> rangos = new ArrayList<>();
	    String[] partes = cadenaRangos.split(",");
	    for (String parte : partes) {
	        String[] extremos = parte.trim().split("-");
	        if (extremos.length != 2) {
	            throw new IllegalArgumentException("Formato incorrecto en el rango: " + parte);
	        }

	        int desde = Integer.parseInt(extremos[0].trim());
	        int hasta = Integer.parseInt(extremos[1].trim());
	        if (desde > hasta) {
	            throw new IllegalArgumentException("El valor inicial del rango debe ser menor o igual que el final: " + parte);
	        }
	        rangos.add(new int[]{desde, hasta});
	    }
	    rangos.sort(Comparator.comparingInt(r -> r[0]));
	    for (int i = 1; i < rangos.size(); i++) {
	        if (rangos.get(i - 1)[1] >= rangos.get(i)[0]) {
	            throw new IllegalArgumentException("Los rangos no pueden solaparse.");
	        }
	        
	    }List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
	    Map<String, List<String>> resultado = new LinkedHashMap<>();
	    for (int[] rango : rangos) {
	        resultado.put(rango[0] + "-" + rango[1], new ArrayList<>());
	    }

	    for (Alumno alumno : alumnos) {
	        int edad = calcularEdad(alumno.fechaNacimiento);
	        for (int[] rango : rangos) {
	            if (edad >= rango[0] && edad <= rango[1]) {
	                resultado.get(rango[0] + "-" + rango[1]).add(alumno.nombre + " (" + edad + " años)");
	                break;
	            }
	        }
	    }

	    return resultado;
	}
  // e 
  public static String nombreProfesorMasGruposFuncional(int edadMin, int edadMax) throws IOException {
	    if (edadMin >= edadMax) {
	        throw new IllegalArgumentException("La edad mínima no puede ser mayor o igual a la edad máxima.");
	    }

	    List<Profesor> profesores = cargarProfesores("centro/profesores.txt");
	    List<Asignacion> asignaciones = cargarAsignaciones("centro/asignaciones.txt");

	    return profesores.stream()
	            .filter(p -> {
	                int edad = p.getEdad();
	                return edad >= edadMin && edad <= edadMax;
	            })
	            .collect(Collectors.toMap(
	                p -> p,
	                p -> asignaciones.stream()
	                        .filter(a -> a.dniProfesor.equals(p.dni))
	                        .map(a -> a.idGrupo)
	                        .collect(Collectors.toSet())
	                        .size()
	            ))
	            .entrySet().stream()
	            .max(Comparator
	                 .comparing(Map.Entry<Profesor, Integer>::getValue) 
	                 .thenComparing(e -> e.getKey().dni, Comparator.reverseOrder())) 
	            .map(entry -> "Nombre: " + entry.getKey().nombre+ " DNI: " + entry.getKey().dni + " ,Edad: " + entry.getKey().getEdad() + ", Nº Grupos: " + entry.getValue())
	            .orElse("No se encontraron profesores en el rango de edad especificado.");
	}

  
  public static String nombreProfesorMasGruposImperativo(int edadMin, int edadMax) throws IOException {
	    if (edadMin >= edadMax) {
	        throw new IllegalArgumentException("La edad mínima no puede ser mayor o igual a la edad máxima.");
	    }

	    List<Profesor> profesores = cargarProfesores("centro/profesores.txt");
	    List<Asignacion> asignaciones = cargarAsignaciones("centro/asignaciones.txt");
	    Profesor profesorConMasGrupos = null;
	    int maxGrupos = 0;


	    for (Profesor p : profesores) {
	        int edad = p.getEdad();
	        if (edad >= edadMin && edad <= edadMax) {
	            Set<Integer> grupos = new HashSet<>();
	            for (Asignacion a : asignaciones) {
	                if (a.dniProfesor.equals(p.dni)) {
	                    grupos.add(a.idGrupo); 
	                }
	            }
	            int cantidadGrupos = grupos.size();

	            if (cantidadGrupos > maxGrupos || 
	               (cantidadGrupos == maxGrupos && (profesorConMasGrupos == null || p.dni.compareTo(profesorConMasGrupos.dni) < 0))) {
	                maxGrupos = cantidadGrupos;
	                profesorConMasGrupos = p;
	            }
	        }
	    }return profesorConMasGrupos != null 
	            ? "Nombre: "+ profesorConMasGrupos.nombre +  " DNI: " + profesorConMasGrupos.dni + " ,Edad: " + profesorConMasGrupos.getEdad() + ", Nº Grupos: " + maxGrupos
	            : "No se encontró profesor en ese rango de edad.";
	}
  // f
  public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, Integer a) throws IOException{
	  if (n == null || a == null) {
		  throw new IllegalArgumentException("Los parámetros no pueden ser nulos");		 
	  }
	  if (n<1 || n> 10) {
		  throw new IllegalArgumentException("El número de alumnos debe estar entre 1 y 10");
	  }
   List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
  return alumnos.stream()
		  .filter(alumno -> alumno.getfechaNacimiento().getYear() > a)
		  .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
		  .limit(n)
		  .map(Alumno::getNombre)
		  .collect(Collectors.toList());
  
		  
  	}
  public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, Integer a) throws IOException{
	  if (n == null || a == null) {
		  throw new IllegalArgumentException("Los parámetros no pueden ser nulos");		 
	  }
	  if (n<1 || n> 10) {
		  throw new IllegalArgumentException("El número de alumnos debe estar entre 1 y 10");
	  }
	  List<Alumno> alumnos = cargarAlumnos("centro/alumnos.txt");
	  List<Alumno> alumnosFiltrados = new ArrayList<>();
		for (Alumno alumno : alumnos) {
			if (alumno.getfechaNacimiento().getYear() > a) {
				alumnosFiltrados.add(alumno);
			}
		}
   alumnosFiltrados.sort(Comparator.comparingDouble(Alumno::getNota).reversed());
  List<String> nombresAlumnos = new ArrayList<>();
  for (int i =0; i < Math.min(n, alumnosFiltrados.size()); i++) {
	  nombresAlumnos.add(alumnosFiltrados.get(i).getNombre());
	}return nombresAlumnos;
}

}

  

    	
    
   




