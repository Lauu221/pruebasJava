
package etrega2Tipos;
import java.util.LinkedList;



public class Pila<E> extends AgregadoLineal<E>  {
	private LinkedList<E> elementos;
	
	public Pila() {
		this.elementos = new LinkedList<>();
	}
	
	
	public void add(E e) {
		elementos.addFirst(e);
	}
	 public E top() {
			if (elementos.isEmpty()) {
				throw new IllegalStateException("No hay elementos en la pila");
			}
			return elementos.getFirst();
	 }
	 
	 public E pop() {
		 if (elementos.isEmpty()) {
                throw new IllegalStateException("No hay elementos en la pila");
            }
		 return elementos.remove(0);

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

	public E remove() {
		return null;
	}


	
		
		
	}
	

