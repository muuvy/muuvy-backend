package org.muuvy.backend.business.dao;

import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FavoriteDAO {

	@Inject
	private EntityManager em;

	public List<Favorite> getAll() {
		em.getTransaction().begin();
		return em.createNamedQuery("Favorite.findAll", Favorite.class).getResultList();
	}

	public Favorite findById(String id) {
		em.getTransaction().begin();
		return em.find(Favorite.class, id);
	}

	public void update(Favorite favorite) {
		em.getTransaction().begin();
		em.merge(favorite);
		em.getTransaction().commit();
	}

	public void create(Favorite favorite) {
		em.getTransaction().begin();
		em.persist(favorite);
		em.getTransaction().commit();
	}

	public void delete(Favorite favorite) {
		em.getTransaction().begin();
		em.remove(favorite);
		em.getTransaction().commit();
	}
}
