package entrega6.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import entrega6.preguntas.preguntasCentro;


public class testCentro {
	 
	 public static void main(String[] args) throws IOException {
		preguntasCentro centro = new preguntasCentro();
		
		System.out.println("Ejercicio a: "); 
		 String dni = "88282662Z";

	        System.out.println("\nPromedio edad (Imperativo): " + preguntasCentro.promedioEdadProfesoresImperativo(dni));
	        System.out.println("Promedio edad (Funcional): " + preguntasCentro.promedioEdadProfesoresFuncional(dni));
		
	        System.out.println("\nEjercicio b: ");

	        int grupoDiversidadFuncional = preguntasCentro.grupoMayorDiversidadEdadFuncional();
	        System.out.println("\nGrupo con mayor diversidad de edad (Funcional): " + grupoDiversidadFuncional);
	        
	   
	        int grupoDiversidadImperativo = preguntasCentro.grupoMayorDiversidadEdadImperativo();
	        System.out.println("Grupo con mayor diversidad de edad (Imperativo): " + grupoDiversidadImperativo);
	   
	        System.out.println("\nEjercicio c: ");
	        String alumnoMasMatriculas = preguntasCentro.alumnoMasMatriculasFuncional();
	        System.out.println("\nAlumno con más matrículas (Funcional): " + alumnoMasMatriculas);
	        
	        String alumnoMasMatriculasImperativo = preguntasCentro.alumnoMasMatriculasImperativo();
	        System.out.println("Alumno con más matrículas (Imperativo): " + alumnoMasMatriculasImperativo);
	 
	        System.out.println("\nEjercicio d: ");
	        try {
	            Map<String, List<String>> rangosEdadPorAlumno = preguntasCentro.rangosEdadPorAlumnoFuncional("20 - 21");

	            rangosEdadPorAlumno.forEach((rango, alumnos) -> {
	                System.out.println("Rango " + rango + ":");
	                if (alumnos.isEmpty()) {
	                    System.out.println("  No hay alumnos en este rango.");
	                } else {
	                    alumnos.forEach(nombre -> System.out.println("  " + nombre));
	                }
	            });
	        } catch (IllegalArgumentException e) {
	            System.out.println("Error en formato de rangos: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("Error al cargar datos: " + e.getMessage());
	        }
	        
	        System.out.println("\nEjercicio e: ");
	        String nombreProfesorMasGrupos = preguntasCentro.nombreProfesorMasGruposFuncional(20,30);
	        System.out.println("\nProfesor con más grupos (Funcional): " + nombreProfesorMasGrupos);
	 
	        String nombreProfesorMasGruposImperativo = preguntasCentro.nombreProfesorMasGruposImperativo(20,30);
	        System.out.println("Profesor con más grupos (Imperativo): " + nombreProfesorMasGruposImperativo);
	 
	        System.out.println("\nEjercicio f: ");
	        int n = 3;
	        int a = 2000;
	     	      
	        List<String> nombresAlumnosMayorNotaImperativo = preguntasCentro.nombresAlumnosMayorNotaImperativo(n, a);
	        System.out.println("\nLos " + n + " alumnos nacidos después del año "+ a +" son(imperativo): " +nombresAlumnosMayorNotaImperativo);
	        List<String> nombresAlumnosMayorNotaFuncional = preguntasCentro.nombresAlumnosMayorNotaFuncional( n, a);
	        System.out.println("Los " + n +" alumnos nacidos después del año "+ a +" son (funcional): " +nombresAlumnosMayorNotaFuncional);
	 }
	    
	     
	 
	 
	 
 }

	 
	
	
	

