package entrega6.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entrega6.preguntas.preguntasBancos;
import entrega6.preguntas.preguntasBancos.Cliente;
import entrega6.preguntas.preguntasBancos.Prestamo;

public class testBancos {
	public static void main(String [] args) throws Exception {
		
		String Personasruta = "bancos/personas.txt";
		String Prestamosruta = "bancos/prestamos.txt";
		List<Cliente> clientes = Prestamo.cargarClientesYPrestamos(Personasruta, Prestamosruta);

		preguntasBancos Bancos = new preguntasBancos(); 
		int edadMax = 55;
		double valorMin = 1000;
		double valorMax= 40000;
		LocalDate fechaLimite = LocalDate.of(2023, 2, 2);
		
		 System.out.println("---- FUNCIONAL ----");
		 Map<String, Double> resultadoFuncional = Prestamo.valorTotalPrestamosFuncinal(clientes, edadMax, valorMin, valorMax, fechaLimite);
	        resultadoFuncional.forEach((nombre, suma) -> System.out.println(nombre + ": " + suma));
		
	
		    System.out.println("\n---- IMPERATIVO ----");
		    Map<String, Double> resultadoImperativo = Prestamo.valorTotalPrestamosImperativo(clientes, edadMax, valorMin, valorMax, fechaLimite);
	        resultadoImperativo.forEach((nombre, suma) -> System.out.println(nombre + ": " + suma));

}
}