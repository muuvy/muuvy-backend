package org.muuvy.backend.business.dao;

import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class FavoriteDAO {

	@PersistenceContext(unitName = "muuvy")
	private EntityManager em;

	public List getAll() {
		return em.createNamedQuery("Favorite.findAll", Favorite.class).getResultList();
	}

	public Favorite findById(String id) {
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
