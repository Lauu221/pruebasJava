
package examen4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Examen {
	
	 // Actividad 1

    public static long productoImpares(int n) {
        if (n <= 0) {
            System.out.println("El número debe de ser mayor a 0");
            return 0;
        }
        long producto = 1;
        int numeroImpar = 1;

        for (int i = 0; i < n; i++) {
            producto *= numeroImpar;
            numeroImpar += 2;
        }
        return producto;
    }
    
    // Actividad 2
    
    public static double sumaGeometricaAlternada(double a1, double r, int k) {
        // excepciones
        if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }
        if (a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos");
        }

        double suma = 0.0;
        
        for (int n = 1; n <= k; n++) {
            // Aplicamos la fórmula 
            double termino = Math.pow(-1, n) * a1 * Math.pow(r, n - 1);
            suma += termino;
        }

        return suma;
        
    }
        // Actividad 3
        
    public static long combinatorioSinMultiplosDeTres(int n, int k) {
        // Excepciones
        if (n < k || n <= 0 || k <= 0) {
            throw new IllegalArgumentException("n y k deben ser positivos y n debe ser mayor o igual a k.");
        }

        long resultado = 1;

        for (int i = 0; i < k; i++) {
            int num = n - i;   
            int denom = k - i; 
            
            // Si el numerador o el denominador son múltiplos de 3, los omitimos
            if (num % 3 != 0) {
                resultado *= num;  
            } else {              
                if (denom % 3 != 0) {
                    resultado /= denom;
                }
            }
        }

        return resultado;
    }
    
    
        
        // para la actividad 4 he utilizado el archivo de palabras random de la entrega anterior 
    	
    // Actividad 4
    
    public static List<String> filtrarLineasConsecutivas(String nombreArchivo, List<String> palabrasClave) {
        List<String> lineasFiltradas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                if (contienePalabrasConsecutivas(linea, palabrasClave)) {
                    lineasFiltradas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lineasFiltradas;
    }
   
    private static boolean contienePalabrasConsecutivas(String linea, List<String> palabrasClave) {
        String[] palabras = linea.split("\\s+");
        for (int i = 0; i < palabras.length - 1; i++) {
            
            if (palabrasClave.contains(palabras[i]) && palabrasClave.contains(palabras[i + 1])) {
                return true; 
            }
        }
        return false; 
    }
    
    
    
 
// Resultados 
    
    // Actividad 1
    
    public static void main(String[] args) {
        // Valor de n
    	System.out.println("Actividad 1:");
        int n = 5;
        long resultado = productoImpares(n);
        if (resultado != 0) {
            System.out.println("El producto de los primeros " + n + " números impares es: " + resultado);
        // caso2
        int n2 = 0;
        System.out.println(" ");
        System.out.println("caso de excepción con n = " + n2 + ":");
        long resultado2 = productoImpares(n2);
		if (resultado2 != 0) {
			System.out.println("El producto de los primeros " + n2 + " números impares es: " + resultado2);
		}
	}
	
    
    // Actividad 2
    
    try {
        double a1 = 3.0;
        double r = 2.0;
        int k2 = 5;
        System.out.println("____________");
        System.out.println("Actividad 2:");
        double resultado2 = sumaGeometricaAlternada(a1, r, k2);
        System.out.println("La suma de los primeros " + k2 + " términos es: " + resultado2);
        
        // caso 2
        double a12 = -3.0;
        double r2 = 2.0;
        int k22 = 5;
        System.out.println(" ");
        System.out.println("caso de excepción con a1 = " + a12 + ":");
        double resultado22 = sumaGeometricaAlternada(a12, r2, k22);
        System.out.println("La suma de los primeros " + k22 + " términos es: " + resultado22);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());    
    }
     
  
    
    // Actividad 3
        try {
            int n3 = 10;
            int k3 = 3;
            System.out.println("____________");
            System.out.println("Actividad 3:");
            long resultado3 = combinatorioSinMultiplosDeTres(n3, k3);
            System.out.println("El valor del numero combinatorio de " + n3 + " y " + k3 + " sin múltiplos de 3 es: " + resultado3);
            
            // caso 2
            int n32 = 5;
            int k32 = 6;
            System.out.println(" ");
            System.out.println("caso de excepción con k = " + k32 + " y n = " + n32 + ":");
            long resultado32 = combinatorioSinMultiplosDeTres(n32, k32);
            System.out.println("El valor del numero combinatorio de " + n32 + " y " + k32 + " sin múltiplos de 3 es: " + resultado32);           
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    
    
    // Actividad 4
        System.out.println("____________");
        System.out.println("Actividad 4:");
    List<String> palabrasClave = Arrays.asList("salud", "proyecto", "tecnología");
    String nombreArchivo = "src/resources/archivo_palabras.txt";
    
    List<String> lineasConsecutivas = filtrarLineasConsecutivas(nombreArchivo, palabrasClave);
    
    System.out.println("Líneas que contienen palabras consecutivas:");
    for (String linea : lineasConsecutivas) {
        System.out.println(linea);
    }
}
}






    

