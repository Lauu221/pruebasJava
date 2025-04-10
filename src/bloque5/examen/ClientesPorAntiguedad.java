
package bloque5.examen;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import etrega2Tipos.ListaOrdenada;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {
	public ClientesPorAntiguedad() {
		super(Comparator.comparingInt(Cliente::getAntiguedad).reversed());
	}
	
	public List<Cliente> topClientes(int n){
		List<Cliente> resultado = new ArrayList<>();
		int contador = 0;
		
		for (Cliente cliente : this) {
			if (contador >=n) break;
			resultado.add(cliente);
			contador++;
		} 
		return resultado;
	
		//TEST
		
		
	}
	 public static class Test {

	        public static void main(String[] args) {

	            Cliente ana = Cliente.of("Ana", 5);
	            Cliente juan = Cliente.of("Juan", 2);
	            Cliente luis = Cliente.of("Luis", 7);
	            Cliente maria = Cliente.of("Maria", 3);

	            
	            ClientesPorAntiguedad clientesPorAntiguedad = new ClientesPorAntiguedad();
	            clientesPorAntiguedad.add(ana);
	            clientesPorAntiguedad.add(juan);
	            clientesPorAntiguedad.add(luis);
	            clientesPorAntiguedad.add(maria);

	            
	            int n = 3;
	            List<Cliente> topClientes = clientesPorAntiguedad.topClientes(n);

	            
	            System.out.println("Los " + n + " clientes con más antigüedad son:");
	            for (Cliente cliente : topClientes) {
	                System.out.println(cliente);
	            }
	        }
	    }
	}