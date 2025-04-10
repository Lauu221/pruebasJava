
package bloque5.examen;

import java.util.Objects;

public class Cliente {
	private final String nombre;
	private final int antiguedad;
	

	public Cliente(String nombre, int antiguedad) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}if (antiguedad < 0) {
			throw new IllegalArgumentException("La antigüedad no puede ser negativa");
		}
		this.nombre = nombre;
		this.antiguedad = antiguedad;
			}

	public static Cliente of(String nombre, int antiguedad) {
		return new Cliente(nombre, antiguedad);
			}
	public String getNombre() {
		return nombre;
		
	}

	public int getAntiguedad() {
		return antiguedad;

	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
    }
	
}
