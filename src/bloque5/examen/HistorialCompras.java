package bloque5.examen;

import java.util.ArrayList;
import java.util.List;

import etrega2Tipos.Pila;

public class HistorialCompras extends Pila<Compra> {

    public HistorialCompras() {
        super();
    }

    
    public double totalGastadoPor(Cliente cliente) {
        double total = 0.0;
        for (Compra compra : getElementos()) { 
            if (compra.getCliente().equals(cliente)) {
                total += compra.getImporte();
            }
        }
        return total;
    }

   
    public List<Compra> compraMayoresA(double cantidad) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra compra : getElementos()) {  
            if (compra.getImporte() > cantidad) {
                resultado.add(compra);
            }
        }
        return resultado;
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

            
            HistorialCompras historial = new HistorialCompras();
            historial.add(c1);
            historial.add(c2);
            historial.add(c3);
            historial.add(c4);
            historial.add(c5);

            
            double totalGastadoPorAna = historial.totalGastadoPor(ana);
            System.out.println("Total gastado por Ana: " + totalGastadoPorAna + "€");

            
            double cantidad = 30.0;
            List<Compra> comprasMayoresA = historial.compraMayoresA(cantidad);
            System.out.println("Compras con importe mayor a " + cantidad + "€:");
            for (Compra compra : comprasMayoresA) {
                System.out.println(compra);
            }
        }
    }
}
