package com.app.presentacion;
import java.util.List;
import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
	public static void main(String[] args) {
	// INSTANCIAR DOS ARTISTAS
		Artista artista1 = new Artista();
		Artista artista2 = new Artista();
		
	// INSTANCIAR DOS CANCIONES
		Cancion cancion1 = new Cancion();
		Cancion cancion2 = new Cancion();
		
		ControladoraLogica conLog = new ControladoraLogica();
		
		
		
		try {
		// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES
			artista1.setNombre("Marce");
			artista1.setGeneroMusical("Rock");
			artista1.setNacionalidad("Argentino");
			artista1.setEdad(17);
			
			conLog.crearArtista(artista1);
			
			artista2.setNombre("July");
			artista2.setGeneroMusical("Pop");
			artista2.setNacionalidad("Argentina");
			artista2.setEdad(17);
			
			conLog.crearArtista(artista2);
			
			cancion1.setTitulo("rockero Marce");
			cancion1.setDuracion(4);
			cancion1.setAnio(2026);

			conLog.crearCancion(cancion1);
			
			cancion2.setTitulo("cancionsita de July");
			cancion2.setDuracion(3);
			cancion2.setAnio(2026);
			
			conLog.crearCancion(cancion2);
			// ----- LECTURA DE ARTISTAS -----
			System.out.println("=== TODOS LOS ARTISTAS ===");
			List<Artista> artistas = conLog.listarArtistas();
			for (Artista a : artistas) {
			System.out.println(a);
			}
			System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
			Artista encontrado = conLog.buscarUnArtista(1);
			if (encontrado != null) {
			System.out.println(encontrado);
			} else {
			System.out.println("No existe un artista con ese id");
			}
			System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
			Artista noExiste = conLog.buscarUnArtista(999);
			if (noExiste != null) {
			System.out.println(noExiste);
			} else {
			System.out.println("No existe un artista con ese id");
			}
		} 
		catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}

	}
}



