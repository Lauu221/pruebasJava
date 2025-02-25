
package entrega1;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Lecturas {	
	
	// Act 6 
	public static int contarPalabras(String nombreFichero, String separador, String palabra) {
        int contador = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
        	String linea;
        	
        	while ((linea = br.readLine()) != null) {
        		String[] palabras = linea.split(separador);
        		
        		for (String p: palabras) {
					if (p.equalsIgnoreCase(palabra)) {
						contador++;
					}
        		}
        		
        	}
        } catch (IOException e) {
        	System.out.println("Error: " + e.getMessage());
        }
          return contador; 
            }
	
	// Act 7
	
	public static List<String> lineasFichero(String nombreFichero, String cadena){
		List<String> lineas = new ArrayList<>();
		String cadenaMinusculas = cadena.toLowerCase();
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))){
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.toLowerCase().contains(cadenaMinusculas)) { 
                    lineas.add(linea);
			
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return lineas;			
	}
	
	// Act 8
	public static List<String> buscarPalabras(String nombreFichero){
		Set<String> palabrasUnicas = new HashSet<>();
		try(BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                
                for (String palabra: palabras) {
                   if (!palabra.isEmpty()) {
                	   palabrasUnicas.add(palabra);
                   }
                }
            }
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return new ArrayList<>(palabrasUnicas);
		
	}
	
	// Act 8 
	public static double longitudMedia(String nombreFichero) {
		int longitudtotal = 0;
		int lineas = 0;		
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				longitudtotal += linea.length();
				lineas++;			
			}
				
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			return 0;
			
		}
		if (lineas == 0) {
			return 0;
		}
		return (double) longitudtotal / lineas;
	}
	
	
	public static void main(String[] args) {	
		
	 String path = "src/resources/lin_quijote.txt";
	 String palabras = "Quijote";
	 String separador = "\\s+";
	 int resultado5 = contarPalabras(path, separador, palabras);
	 System.out.println("Actividad 6 : ");
	 System.out.println("La palabra '" + palabras + "' aparece " + resultado5 + " veces.");
	 
	 
	 String path2 = "src/resources/lin_quijote.txt";
     String cadena = "Quijote"; 
     List<String> resultado = lineasFichero(path2, cadena);
     
     System.out.println("______________");
	 System.out.println("Actividad 7 :");
     System.out.println("Líneas que contienen '" + cadena + "':");
     for (String linea : resultado) {
         System.out.println(linea);
	 
	 }
     String path3 = "src/resources/archivo_palabras.txt"; 

     List<String> palabrasUnicas = buscarPalabras(path3);
     System.out.println("______________");
	 System.out.println("Actividad 8 :");
     System.out.println("las palabras únicas en el archivo son:" + palabrasUnicas);
     
     
     String path4 = "src/resources/palabras_random.csv";
     double longitud = longitudMedia(path4);
     System.out.println("______________");
	 System.out.println("Actividad 9 :");
     System.out.println("la longitud media es:" + longitud);

	}	
}
