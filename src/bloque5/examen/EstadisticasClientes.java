package bloque5.examen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EstadisticasClientes {
	 public static Map<Cliente, List<Compra>> agruparComprasPorCliente(List<Compra> compras) {
	        Map<Cliente, List<Compra>> comprasPorCliente = new HashMap<>();

	        for (Compra compra : compras) {
	            Cliente cliente = compra.getCliente();
	            // Si el cliente ya tiene una lista, añadimos la compra, si no, creamos una nueva lista
	            comprasPorCliente
	                .computeIfAbsent(cliente, k -> new ArrayList<>())
	                .add(compra);
	        }

	        return comprasPorCliente;
	    }

}
