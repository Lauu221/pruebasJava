

package entrega1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Funciones {
	//Act 1
	public static int producto(int n, int k) {
			if(n<= k){	
				throw new IllegalArgumentException("Error: n debe de ser mayor que k");	
			}
			int producto = 1;
			for(int i = 0; i <= k; i++ ) {
				producto *= (n - i + 1);			
			}	
			return producto;					
	}
	// Act 2
	public static int secuenciaGeometrica(int ai, int r, int k2) {
		int producto2 = 1;
		for(int n2=0; n2 < k2; n2++) {
			producto2 *= ai * Math.pow(r, n2);
		}
	    return producto2;
	    
	}
	// Act 3
	private static int factorial(int i) {
		int fact = 1;
		for (int j = 2; j <= i; j++){
			fact *= j;
		}
		return fact;
	}	
	
	public static long binomial(int n, int k3) {
		if (k3 > n) 
			return 0;
		return factorial(n)/(factorial(k3)*factorial(n-k3));
		
	}
	// Act 4
	public static double suma(int n3, int k4) {
		double sum = 0; 
		for (int i = 0; i < k4; i++) {
			int signo = (int)Math.pow(-1, i);
			
			double binom = binomial(k4 +1, i+1);
			
			double pow = Math.pow(k4-i, n3);
			
			sum += signo*binom*pow;
		
		}
		return sum/(double)factorial(k4);
		
	}
	// Act 5
	
	public static double funcion(double x) {
		return Math.pow(x, 2)*2;
		}
		
	public static double derivada(double x) {
		return 4*x;
	}

	public static double metodoNewton(double a, double epsilon, int iterMax) {
		double x = a;
		int iter = 0;
		while (Math.abs(funcion(x)) > epsilon && iter < iterMax) {
			double deriv = derivada(x);
			if (deriv == 0) {
				throw new ArithmeticException("Error: derivada nula");
            }
			x = x - funcion(x) / deriv;
			iter++;
		}
		return x;
	}
		
	
	
		
// Pruebas 
	
	public static void main(String[] args) {
		try {
			int n = 4;
			int k = 2;
			int resultado = producto(n, k);
			System.out.println("Actividad 1 : ");
			System.out.println("El producto es: " + resultado);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}	
		int ai = 3;
		int r = 5;
		int k2 = 2; 
		int resultado2 = secuenciaGeometrica(ai, r, k2);
		System.out.println("_________________" );
		System.out.println("Actividad 2 : " );
		System.out.println("El producto de la secuencia geometrica es: " + resultado2 );			
		
		
		int k3 = 2;
		int n2 = 4; 
		long resultado3 = binomial( n2, k3);
		System.out.println("_________________" );
		System.out.println("Actividad 3 : " );
		System.out.println("El numero combinatorio es: " + resultado3 );
		
		
		int k4 = 2;
		int n3 = 4;
		double resultado4 = suma(n3, k4);
		System.out.println("_________________" );
		System.out.println("Actividad 4 : " );
		System.out.println("El número S(n,k) siendo" + " k="+ k4 +" n=" + n3 + " es:" + resultado4);
		
		double a = 3;
		double epsilon = 0.001;
		int iterMax = 100;
		 try {
	            double raiz = metodoNewton(a, epsilon, iterMax);
	            System.out.println("_________________" );
	            System.out.println("Actividad 5 : " );
	            System.out.println("siendo a = " + a + " y epsilon = "+ epsilon+ " la raíz encontrada es: " + raiz);
	        } catch (ArithmeticException e) {
	            System.out.println("Error: " + e.getMessage());
	     
	        }				 
    }
		 
		 
		}
	
	
