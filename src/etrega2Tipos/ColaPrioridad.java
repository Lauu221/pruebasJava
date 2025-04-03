
package etrega2Tipos;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;


public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<ColaPrioridad.PriorityElement<E, P>> {

    private PriorityQueue<PriorityElement<E, P>> elementos;

    private ColaPrioridad() {  	
    	this.elementos = new PriorityQueue<>();
    }

    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }  
    

    public void add(E value, P priority) {
        elementos.add(new PriorityElement<>(value, priority));
    }

    public List<E> valuesAsList() {
    	 List<E> listaValores = new ArrayList<>();
         PriorityQueue<PriorityElement<E, P>> copia = new PriorityQueue<>(elementos);
         while (!copia.isEmpty()) {
             listaValores.add(copia.poll().value());
         }
         return listaValores;
    }

    public void decreasePriority(E value, P newPriority) {
        PriorityElement<E, P> toModify = null;

        for (PriorityElement<E, P> element : elementos) {
            if (element.value().equals(value)) {
                toModify = element;
                break;
            }
        }

        if (toModify != null) {
            elementos.remove(toModify);
            elementos.add(new PriorityElement<>(value, newPriority));
        }
    }

    public E removeValue() {
		if (isEmpty()) {
			throw new IllegalStateException("No hay elementos en la cola");
		}
		return elementos.poll().value();
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(value, priority);
        }
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public int size() {
        return elementos.size();
    }

    @Override
    public String toString() {
        return elementos.toString();
    }

    public record PriorityElement<E, P extends Comparable<P>>(E value, P priority)
            implements Comparable<PriorityElement<E, P>> {

        @Override
        public int compareTo(PriorityElement<E, P> other) {
            return this.priority.compareTo(other.priority);
        }
    }
}