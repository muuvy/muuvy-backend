package org.muuvy.backend.business.dao;

import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FavoriteDAO {

	@Inject
	private EntityManagerProducer emProducer;

	public List<Favorite> getAll() {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		return em.createNamedQuery("Favorite.findAll", Favorite.class).getResultList();
	}

	public Favorite findById(String id) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		return em.find(Favorite.class, id);
	}

	public void update(Favorite favorite) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.merge(favorite);
		em.getTransaction().commit();
	}

	public void create(Favorite favorite) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.persist(favorite);
		em.getTransaction().commit();
	}

	public void delete(Favorite favorite) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.remove(favorite);
		em.getTransaction().commit();
	}
}
