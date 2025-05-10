package entrega6.preguntas;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class preguntasAeropuerto {
	
	public static class Aeropuerto {
        private String nombre;
        private String pais;
        private String codigoAeropuerto;
        private String ciudad;

        public Aeropuerto(String nombre, String pais, String codigoAeropuerto, String ciudad) {
            this.nombre = nombre;
            this.pais = pais;
            this.codigoAeropuerto = codigoAeropuerto;
            this.ciudad = ciudad;
        }

        public String getcodigoAeropuerto() {
            return codigoAeropuerto;
        }

        public String getciudad() {
            return ciudad;
        }
    }

    public static class Vuelo {
        private String codigoVuelo;
        private String origen;
        private String destino;
        private double precio;

        public Vuelo(String codigoVuelo, String origen, String destino, double precio) {
            this.codigoVuelo = codigoVuelo;
            this.origen = origen;
            this.destino = destino;
            this.precio = precio;
        }

        public String getcodigoVuelo() { return codigoVuelo; }
        public String getorigen() { return origen; }
        public String getdestino() { return destino; }
        public double getprecio() { return precio; }
    }

    public static class OcupacionVuelo {
        private String codigoVuelo;
        private LocalDateTime fechaHora;
        private int pasajeros;

        public OcupacionVuelo(String codigoVuelo, LocalDateTime fechaHora, int pasajeros) {
            this.codigoVuelo = codigoVuelo;
            this.fechaHora = fechaHora;
            this.pasajeros = pasajeros;
        }

        public String getcodigoVuelo() { return codigoVuelo; }
        public LocalDateTime getfechaHora() { return fechaHora; }
        public int getpasajeros() { return pasajeros; }
    }
    

    public static List<Aeropuerto> cargarAeropuertos(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta), StandardCharsets.ISO_8859_1)
                .map(line -> {
                    String[] partes = line.split(",", -1);
                    if (partes.length >= 4) {
                        return new Aeropuerto(partes[0], partes[1], partes[2], partes[3]);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Vuelo> cargarVuelos(String ruta) throws IOException {
        return Files.lines(Paths.get(ruta), StandardCharsets.ISO_8859_1)
                .map(line -> {
                    String[] partes = line.split(",", -1);
                    if (partes.length >= 5) {
                        String codigoVuelo = partes[0] + partes[1];
                        return new Vuelo(codigoVuelo, partes[2], partes[3], Double.parseDouble(partes[4]));
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<OcupacionVuelo> cargarOcupaciones(String ruta) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Files.lines(Paths.get(ruta), StandardCharsets.ISO_8859_1)
                .map(line -> {
                    String[] partes = line.split(",", -1);
                    if (partes.length >= 3) {
                        return new OcupacionVuelo(
                                partes[0],
                                LocalDateTime.parse(partes[1], formatter),
                                Integer.parseInt(partes[2]));
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
	
// preguntas
		
		public static String ciudadAeropuertoMayorFacturacionImperativo(List<Aeropuerto> aeropuertos,
	    List<Vuelo> vuelos,
	    List<OcupacionVuelo> ocupaciones,
	    LocalDateTime fechaInicio,
	    LocalDateTime fechaFin) throws IOException {

	    if (fechaInicio.isAfter(fechaFin) || fechaFin.minusDays(1).isBefore(fechaInicio)) {
	        throw new IllegalArgumentException("Error en el rango de fechas");
	    }

	    Map<String, Double> facturacionPorAeropuerto = new HashMap<>();

	    for (OcupacionVuelo ocupacion : ocupaciones) {
	        if (!ocupacion.getfechaHora().isBefore(fechaInicio) && !ocupacion.getfechaHora().isAfter(fechaFin)) {
	            for (Vuelo vuelo : vuelos) {
	                if (vuelo.getcodigoVuelo().equals(ocupacion.getcodigoVuelo())) {
	                    double facturacion = vuelo.getprecio() * ocupacion.getpasajeros();
	                    facturacionPorAeropuerto.put(
	                        vuelo.getdestino(),
	                        facturacionPorAeropuerto.getOrDefault(vuelo.getdestino(), 0.0) + facturacion
	                    );
	                }
	            }
	        }
	    }String aeropuertoConMayorFacturacion = null;
	    double mayorFacturacion = 0.0;

	    for (Map.Entry<String, Double> entry : facturacionPorAeropuerto.entrySet()) {
	        if (entry.getValue() > mayorFacturacion) {
	            mayorFacturacion = entry.getValue();
	            aeropuertoConMayorFacturacion = entry.getKey();
	        }
	    }if (aeropuertoConMayorFacturacion == null) {
	        return "No se encontró información";
	    }for (Aeropuerto aeropuerto : aeropuertos) {
	        if (aeropuerto.getcodigoAeropuerto().equals(aeropuertoConMayorFacturacion)) {
	            return aeropuerto.getciudad();
	        }
	    }return "Ciudad no encontrada";
	}
	
	
	public static String ciudadAeropuertoMayorFacturacionFuncional(
	List<Aeropuerto> aeropuertos,
	List<Vuelo> vuelos,
	List<OcupacionVuelo> pasajeros,
	LocalDateTime fechaInicio,
	LocalDateTime fechaFin) throws IOException{
		
		if (fechaInicio.isAfter(fechaFin)|| fechaFin.minusDays(1).isBefore(fechaInicio)) {
			throw new IllegalArgumentException("Error en el rango de fechas");
        }
		return pasajeros.stream()
				.filter(p -> !p.getfechaHora().isBefore(fechaInicio) && !p.getfechaHora().isAfter(fechaFin))
                .flatMap(ocu -> vuelos.stream()
                        .filter(v -> v.getcodigoVuelo().equals(ocu.getcodigoVuelo()))
                        .map(v -> Map.entry(v.getdestino(), v.getprecio() * ocu.getpasajeros())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingDouble(Map.Entry::getValue)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> aeropuertos.stream()
                        .filter(a -> a.getcodigoAeropuerto().equals(entry.getKey()))
                        .map(Aeropuerto::getciudad)
                        .findFirst()
                        .orElse("Ciudad no encontrada"))
                .orElse("No se encontró información");
    	}
		
	}

	
	


