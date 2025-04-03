
package etrega2Tipos;
import java.util.LinkedList;


public class Cola<E> extends AgregadoLineal<E> {
	private LinkedList<E> elementos;
	
	protected Cola() {
        this.elementos = new LinkedList<>();
    }
	
	
	public static <E> Cola<E> of(){
		return new Cola<>();		
	}
	
	@Override
	public void add(E e) {
		elementos.add(e);
    
	}
	public E remove() {
		return null;
	}
	
	public E poll() {
		if (elementos.isEmpty()) {
			throw new IllegalStateException("No se puede eliminar de un agragado vacío");
		}
		return elementos.remove(0);
		
	}
	
	public E peek() {
		return elementos.isEmpty() ? null : elementos.get(0);
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


	
}



