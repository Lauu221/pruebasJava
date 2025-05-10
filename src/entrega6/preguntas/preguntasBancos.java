package entrega6.preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;

public class preguntasBancos {
	
	
	public static class Cliente {
		private String nombre;
		private String dni;
		private LocalDate fechaNacimiento;
		private List<Prestamo> prestamos = new ArrayList<>();
		
		public Cliente(String nombre, String dni, LocalDate fechaNacimiento) {
			this.nombre = nombre;
			this.dni = dni;
			this.fechaNacimiento = fechaNacimiento;
			
		}

		public void agregarPrestamo(Prestamo prestamo) {
			prestamos.add(prestamo);
		}
		
		public List<Prestamo> getPrestamos() {
			return prestamos;
		}

		public String getNombre() {
			return nombre;
		}

		public String getDni() {
			return dni;
		}

		public LocalDate getFechaNacimiento() {
			return fechaNacimiento;
		}
	}public static class Prestamo{
		private final double valor;
		private final LocalDate fecha;
		
		public Prestamo(double valor, LocalDate fecha) {
			this.valor = valor;
			this.fecha = fecha;
		}

		public double getValor() {
			return valor;
		}

		public LocalDate getFecha() {
			return fecha;
		}
		
		public static List<Cliente> cargarClientesYPrestamos(String personasruta, String prestamosruta) throws IOException {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 Map<String, Cliente> clientes = Files.lines(Paths.get(personasruta))
					 .map(line -> line.split(","))
		                .collect(Collectors.toMap(
		                        parts -> parts[2],
		                        parts -> new Cliente(
		                                parts[0] + " " + parts[1],
		                                parts[2],
		                                LocalDateTime.parse(parts[3], formatter).toLocalDate()
		                        )
		                ));
		
			 Files.lines(Paths.get(prestamosruta))
			 	.map(line -> line.split(","))
			 	.forEach(parts -> {
             		String dni = parts[1];
             		double valor = Double.parseDouble(parts[5]);
             		LocalDate fecha = LocalDateTime.parse(parts[3], formatter).toLocalDate();

             		
             		Prestamo prestamo = new Prestamo(valor, fecha);
             		Cliente cliente = clientes.get(dni);
             		if (cliente != null) {
             			cliente.agregarPrestamo(prestamo);
             		}
             	});
		
			 return new ArrayList<>(clientes.values());
	}
		//-----------Funcional----------
		
		public static Map<String, Double> valorTotalPrestamosFuncinal(List<Cliente> clientes, int edadMax, double valorMin, double valorMax, LocalDate fechaLimite) {		   
		    validarParametros(edadMax, valorMin, valorMax);
		  
		    return clientes.stream()
		            .filter(c -> {
		                int edad = Period.between(c.getFechaNacimiento(), LocalDate.now()).getYears();
		                return edad < edadMax && 
		                       c.getPrestamos().stream().anyMatch(p -> 
		                           p.getValor() >= valorMin && 
		                           p.getValor() <= valorMax && 
		                           p.getFecha().isAfter(fechaLimite) 
		                       );
		            })
		            .collect(Collectors.toMap(
		                    Cliente::getNombre,
		                    c -> c.getPrestamos().stream()
		                          .filter(p -> p.getValor() >= valorMin && p.getValor() <= valorMax && p.getFecha().isAfter(fechaLimite)) 
		                          .mapToDouble(Prestamo::getValor)
		                          .sum()
		            ));
		}
		//-----------Imperativo----------
		
		public static Map<String, Double> valorTotalPrestamosImperativo(List<Cliente> clientes, int edadMax, double valorMin, double valorMax, LocalDate fechaLimite) {
		    validarParametros(edadMax, valorMin, valorMax);
		  
		    Map<String, Double> resultado = new HashMap<>();
		    for (Cliente cliente : clientes) {
		        
		        int edad = Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears();
		        if (edad < edadMax) {
		            double suma = 0;
		            for (Prestamo prestamo : cliente.getPrestamos()) {
		               
		                if (prestamo.getValor() >= valorMin && prestamo.getValor() <= valorMax && prestamo.getFecha().isAfter(fechaLimite)) {
		                    suma += prestamo.getValor();
		                }
		            }
		            if (suma > 0) {
		                resultado.put(cliente.getNombre(), suma);
		            }
		        }
		    }
		    
		    return resultado;
		}

		private static void validarParametros(int e, double a, double b) {
	        if (e <= 18) throw new IllegalArgumentException("La edad debe ser mayor que 18");
	        if (a <= 0 || b <= 0) throw new IllegalArgumentException("Los valores deben ser positivos");
	        if (a >= b) throw new IllegalArgumentException("El valor 'a' debe ser menor que 'b'");
	    }

	
	}

}
