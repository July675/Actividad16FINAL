package com.app.persistencia;
import java.util.List;
import com.app.logica.Cancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CancionJpaController {
	
	// "Fábrica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qué unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que está en persistence.xml.
	public CancionJpaController() {
		emf = Persistence.createEntityManagerFactory("ejemplojpa");
	}

	// Método que devuelve un nuevo EntityManager (una conexión lista para trabajar).
	// Podés pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para crear (persistir) una Canción en la base de datos.
	public void crear(Cancion canc) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (traemos al empleado).
			em = getEntityManager();
			
			// Iniciamos la transacción (arranca la promesa de que se hará todo o nada).
			em.getTransaction().begin();
			
			// Guardamos el objeto Cancion en la base (todavía no confirmado).
			em.persist(canc);
			
			// Confirmamos la transacción: ahora sí, el dato queda guardado en la BD.
			em.getTransaction().commit();

		} finally {
			// Cerramos la conexión si estaba abierta (el empleado termina su turno).
			if (em != null) {
				em.close();
			}
		}
	}

	// LISTAR TODAS LAS CANCIONES
	public List<Cancion> listar() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Cancion c", Cancion.class).getResultList();
		} finally {
			em.close();
		}
	}

	// BUSCAR UNA CANCION POR ID
	public Cancion buscar(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Cancion.class, id);
		} finally {
			em.close();
		}
	}

	// EDITAR UNA CANCION
	public void editar(Cancion can) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(can);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	// ELIMINAR UNA CANCION
	public void eliminar(int id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			Cancion can = em.find(Cancion.class, id);

			if (can != null) {
				em.remove(can);
			}

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}


