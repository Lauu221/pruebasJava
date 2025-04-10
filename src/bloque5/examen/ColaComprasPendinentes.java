package bloque5.examen;

import java.util.List;
import java.util.stream.Collectors;

import etrega2Tipos.Cola;

public class ColaComprasPendinentes extends Cola<Compra> {

    public ColaComprasPendinentes() {
        super();
    }

    
    public Compra buscarCompraPorDescripcion(String desc) {
        return elementos.stream()
                .filter(compra -> compra.getDescripcion().toLowerCase().contains(desc.toLowerCase()))
                .findFirst()
                .orElse(null); 
    }

    
    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return elementos.stream()
                .filter(compra -> compra.getCliente().equals(cliente) && compra.getDescripcion().toLowerCase().contains(producto.toLowerCase()))
                .collect(Collectors.toList()); 
    }

    // TEST
    public static class Test {

        public static void main(String[] args) {

            
            Cliente ana = Cliente.of("Ana", 5);
            Cliente juan = Cliente.of("Juan", 2);
            Cliente luis = Cliente.of("Luis", 7);
            Cliente maria = Cliente.of("Maria", 3);

           
            Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
            Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
            Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
            Compra c4 = Compra.of(luis, "Poster gigante", 80.0);
            Compra c5 = Compra.of(maria, "Camiseta deportiva", 40.0);

            // Crear cola de compras pendientes
            ColaComprasPendinentes cola = new ColaComprasPendinentes();
            cola.add(c1);
            cola.add(c2);
            cola.add(c3);
            cola.add(c4);
            cola.add(c5);

           
            String descripcionBuscada = "taza";
            System.out.println("Buscando compra con descripción que contenga: " + descripcionBuscada);
            Compra compraEncontrada = cola.buscarCompraPorDescripcion(descripcionBuscada);
            if (compraEncontrada != null) {
                System.out.println("Compra encontrada: " + compraEncontrada);
            } else {
                System.out.println("No se encontró ninguna compra con la descripción que contenga: " + descripcionBuscada);
            }

           
            List<Compra> comprasFiltradas = cola.filtrarPorClienteYProducto(juan, "camiseta");
            System.out.println("\nCompras de Juan que contienen 'camiseta':");
            comprasFiltradas.forEach(compra -> System.out.println(compra));
        }
    }
}
