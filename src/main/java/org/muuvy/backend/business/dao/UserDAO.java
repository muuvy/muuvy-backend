package org.muuvy.backend.business.dao;

import java.util.*;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jboss.logging.Logger;
import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.User;

@ApplicationScoped
public class UserDAO {

	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	@Inject
	public EntityManagerProducer emProducer;

	public List<User> getAll() {
		EntityManager em = emProducer.createEntityManager();
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public Optional<User> findById(String id) {
		EntityManager em = emProducer.createEntityManager();
		User user = em.find(User.class, id);
		return Optional.of(user);
	}

	public User findByName(String name) {

		EntityManager em = emProducer.createEntityManager();

		Query query = em.createQuery("SELECT c FROM User c where fullName = :fullName", User.class);
		query.setMaxResults(1);
		query.setParameter("fullName", name);
		List results = query.getResultList();

		LOG.infov("found {0} for username {1}", results, name);

		if (results != null && results.size() > 0) {
			return (User) results.get(0);
		} else {
			return null;
		}
	}

	public void update(User user) {
		LOG.infov("try to merge user {0}", user.toString());
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	public User create(User user) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}

	public void delete(User user) {
		EntityManager em = emProducer.createEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}

}
