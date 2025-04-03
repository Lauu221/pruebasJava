

package entrega2Test;

import java.util.List;

import etrega2Tipos.ColaPrioridad;

public class TestColaPrioridad {
    public static void main(String[] args) {
        testColaPrioridad();
    }

    private static void testColaPrioridad() {
    	
       

        ColaPrioridad<String, Integer> cola = ColaPrioridad.ofPriority();

        
        System.out.println("\nAñadimos elementos con prioridad:");
        cola.add("Crítico", 1);
        System.out.println("'Crítico' con prioridad 1");
        cola.add("Normal", 3);
        System.out.println("'Normal' con prioridad 3");
        cola.add("Urgente", 2);
        System.out.println("'Urgente' con prioridad 2");
        cola.add("Bajo", 4);
        System.out.println("'Bajo' con prioridad 4");

        
        System.out.println("\nElementos en la cola por prioridad: " + cola.valuesAsList());

        
        System.out.println("\nElementos con sus prioridades: " + cola);

       
        System.out.println("\nTamaño de la cola: " + cola.size());

        
        System.out.println("\nCambiando la prioridad de 'Normal' de 3 a 1:");
        cola.decreasePriority("Normal", 1);
        System.out.println("Elementos con su nueva prioridad: " + cola.valuesAsList());

        
        System.out.println("\nDesencolando elementos por prioridad:");
        while (!cola.isEmpty()) {
            System.out.println("\nDesencolado: " + cola.removeValue());
            System.out.println("Cola restante: " + cola.valuesAsList());
        }

       
        System.out.println("\n¿Está vacía? " + cola.isEmpty());

        
        try {
            cola.removeValue();
        } catch (IllegalStateException e) {
            System.out.println("\nExcepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }

        
        System.out.println("\nPrueba con addAll:");
        cola.addAllValues(List.of("Tarea A", "Tarea B", "Tarea C"), 2);
        System.out.println("\nElementos añadidos en lote con prioridad 2: " + cola.valuesAsList());

        
        cola.add("Tarea Urgente", 1);
        System.out.println("\nDespués de añadir 'Tarea Urgente' con prioridad 1: " + cola.valuesAsList());
    }
}