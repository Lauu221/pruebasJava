package etrega2Tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;

public class ListaOrdenada<E> extends AgregadoLineal<E> implements Iterable<E>{
	private final Comparator<E> comparator;
	
	public ListaOrdenada(Comparator<E> comparator) {
		super(); 
		if (comparator == null) {
            throw new IllegalArgumentException("El Comparator no puede ser null");
        }
		this.comparator = comparator;
	
	}
	
	public static <E> ListaOrdenada<E> of (Comparator<E> comparator){
		return new ListaOrdenada<>(comparator);
	}
	
	private int indexOrder(E e) {
		int index = 0;
		while (index < elementos.size() && comparator.compare(e, elementos.get(index)) > 0) {
            index++;
		}
		return index;
        }
	@Override
	public void add(E e) {
		elementos.add(indexOrder(e), e);
	}
	
	@Override
	public E remove() {
		return isEmpty() ? null : elementos.remove(0);
	}
	 @Override
	 public Iterator<E> iterator() {
	        return elementos.iterator();

}
	 }
	


