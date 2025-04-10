package bloque5.examen;

public class Compra {
	private final Cliente cliente;
	private final String descripcion;
	private final double importe;

	
	public Compra(Cliente cliente, String descripcion, double importe) {
		if (cliente == null) {
			throw new IllegalArgumentException("El cliente no puede ser nulo");
			
		}
		if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }
		if (importe < 0) {
        	throw new IllegalArgumentException("El importe no puede ser negativo");
        }
		this.cliente = cliente;
		this.descripcion = descripcion;
		this.importe = importe;
		
	}

	public static Compra of(Cliente cliente, String descripcion, double importe) {
		return new Compra(cliente, descripcion, importe);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getImporte() {
		return importe;
	}
	@Override
	public String toString() {
		return String.format(
				"Compra [cliente=%s, descripcion=%s, importe=%.2f]",
				cliente.getNombre(), descripcion, importe);
	}
}
