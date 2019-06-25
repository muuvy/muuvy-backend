package org.muuvy.backend.business.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.User;

@ApplicationScoped
public class UserDAO {

	@Inject
	private EntityManagerProducer emProducer;

	public List<User> getAll() {
		EntityManager em = emProducer.createEntityManager();
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public User findById(String id) {
		EntityManager em = emProducer.createEntityManager();
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> findByName(String name) {
		List<User> users = new ArrayList<>();
		EntityManager em = emProducer.createEntityManager();
		List results=  em.
				createQuery("SELECT c FROM User c WHERE c.fullName = : fullName")
				.setParameter("fullName", name)
				.setMaxResults(10)
				.getResultList();

		Iterator iter = results.iterator();
		while (iter.hasNext())
		{
			users.add((User)iter.next());
		}

		return users;
	}

	public void update(User user) {
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
