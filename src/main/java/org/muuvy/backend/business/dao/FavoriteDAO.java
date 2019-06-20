package org.muuvy.backend.business.dao;

import org.muuvy.backend.business.rest.RestApplication;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

@ApplicationScoped
public class FavoriteDAO {

	@PersistenceUnit(unitName = "muuvy")


	public List<Favorite> getAll() {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		return em.createNamedQuery("Favorite.findAll", Favorite.class).getResultList();
	}

	public Favorite findById(String id) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		return em.find(Favorite.class, id);
	}

	public void update(Favorite favorite) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.merge(favorite);
		em.getTransaction().commit();
	}

	public void create(Favorite favorite) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.persist(favorite);
		em.getTransaction().commit();
	}

	public void delete(Favorite favorite) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.remove(favorite);
		em.getTransaction().commit();
	}
}
