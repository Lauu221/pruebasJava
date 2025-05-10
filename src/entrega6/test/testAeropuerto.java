package entrega6.test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import entrega6.preguntas.preguntasAeropuerto;
import entrega6.preguntas.preguntasAeropuerto.Aeropuerto;
import entrega6.preguntas.preguntasAeropuerto.Vuelo;
import entrega6.preguntas.preguntasAeropuerto.OcupacionVuelo;




public class testAeropuerto {

	public static void main(String[] args) {
		try {
			List<preguntasAeropuerto.Aeropuerto> aeropuertos =
                    preguntasAeropuerto.cargarAeropuertos("aeropuertos/aeropuertos.csv");
			
            List<preguntasAeropuerto.Vuelo> vuelos =
                    preguntasAeropuerto.cargarVuelos("aeropuertos/vuelos.csv");

            List<preguntasAeropuerto.OcupacionVuelo> ocupaciones =
                    preguntasAeropuerto.cargarOcupaciones("aeropuertos/ocupacionesVuelos.csv");
		
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime inicio = LocalDateTime.parse("2020-01-01 00:00:00", formatter);
            LocalDateTime fin = LocalDateTime.parse("2020-12-31 23:59:59", formatter);

		
            String ciudadFuncional = preguntasAeropuerto.ciudadAeropuertoMayorFacturacionFuncional(
                    aeropuertos, vuelos, ocupaciones, inicio, fin);
            System.out.println("Funcional - Ciudad con mayor facturación: " + ciudadFuncional);
         
            
            
            String ciudadImperativo = preguntasAeropuerto.ciudadAeropuertoMayorFacturacionImperativo(
                    aeropuertos, vuelos, ocupaciones, inicio, fin);
            System.out.println("Imperativo - Ciudad con mayor facturación: " + ciudadImperativo);

            
        } catch (IOException e) {
            System.err.println("Error leyendo archivos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de parámetros: " + e.getMessage());
        }
    }

		
		
		
	}

