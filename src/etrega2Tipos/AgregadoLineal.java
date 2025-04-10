package etrega2Tipos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AgregadoLineal<E> {
	protected List<E> elementos = new ArrayList<>();
	
	
	public AgregadoLineal() {
		this.elementos = new ArrayList<>();
	
	}
	
	public int size() {
		return elementos.size();	
		
	}
	
	public boolean isEmpty() {
		return elementos.isEmpty();
	}
	
	public List<E> elements(){
		return new ArrayList<>(elementos);
	}
	public abstract void add(E e);
	
	public void addAll(List<E> list) {
		for (E e : list) {
			add(e);
		}
						 
	}
	
	public abstract E remove();
	
	public List<E> removeAll(){
        List<E>removedElements = new ArrayList<>(elementos);
		elementos.clear();
		return removedElements; 
	}

	public <T> Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
