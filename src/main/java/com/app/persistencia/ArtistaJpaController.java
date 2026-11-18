package com.app.persistencia;

import java.util.List;
import com.app.logica.Artista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ArtistaJpaController {

	private EntityManagerFactory emf = null;

	public ArtistaJpaController() {
		emf = Persistence.createEntityManagerFactory("ejemplojpa");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void crear(Artista art) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(art);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// NUEVO: traer todos los artistas
	public List<Artista> listar() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT a FROM Artista a", Artista.class).getResultList();
		} finally {
			em.close();
		}
	}

	// NUEVO: buscar un artista por ID
	public Artista buscar(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Artista.class, id);
		} finally {
			em.close();
		}
	}

	// EDITAR UN ARTISTA
	public void editar(Artista art) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(art);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	// ELIMINAR UN ARTISTA
	public void eliminar(int id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			Artista art = em.find(Artista.class, id);

			if (art != null) {
				em.remove(art);
			}

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}