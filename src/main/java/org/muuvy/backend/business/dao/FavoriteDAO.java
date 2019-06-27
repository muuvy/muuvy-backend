package org.muuvy.backend.business.dao;

import org.jboss.logging.Logger;
import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@ApplicationScoped
public class FavoriteDAO {

	private static final Logger LOG = Logger.getLogger(FavoriteDAO.class);

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
		LOG.infov("try to delete fav by object {0}", favorite.toString());
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(favorite));
		em.getTransaction().commit();
	}

	public void deleteById(String favoriteId) {
		LOG.infov("try to delete favorite by id {0}", favoriteId);
		EntityManager em = emProducer.createEntityManager();
		Query query = em.createQuery("DELETE FROM Favorite f where '_id' = :favoriteId", Favorite.class);
		query.setParameter("favoriteId", favoriteId);

		if (query.executeUpdate() == 1) {
			LOG.infov("successfully deleted fav {0}", favoriteId);
		} else {
			LOG.errorv("failed to delete fav");
		}
	}
}
