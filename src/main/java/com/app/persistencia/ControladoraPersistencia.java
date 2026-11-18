
package com.app.persistencia;

import java.util.List;
import com.app.logica.Artista;
import com.app.logica.Cancion;

public class ControladoraPersistencia {

	ArtistaJpaController artistaJpa = new ArtistaJpaController();
	CancionJpaController cancionJpa = new CancionJpaController();

	// ----- ARTISTAS -----

	public void crearArtista(Artista artista) {
		artistaJpa.crear(artista);
	}

	public List<Artista> listarArtistas() {
		return artistaJpa.listar();
	}

	public Artista buscarUnArtista(int id) {
		return artistaJpa.buscar(id);
	}

	public void editarArtista(Artista artista) {
		artistaJpa.editar(artista);
	}

	public void eliminarArtista(int id) {
		artistaJpa.eliminar(id);
	}

	// ----- CANCIONES -----

	public void crearCancion(Cancion cancion) {
		cancionJpa.crear(cancion);
	}

	public List<Cancion> listarCanciones() {
		return cancionJpa.listar();
	}

	public Cancion buscarUnaCancion(int id) {
		return cancionJpa.buscar(id);
	}

	public void editarCancion(Cancion cancion) {
		cancionJpa.editar(cancion);
	}

	public void eliminarCancion(int id) {
		cancionJpa.eliminar(id);
	}
}
