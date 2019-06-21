package org.muuvy.backend.business.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.muuvy.backend.persistence.models.User;

@ApplicationScoped
public class UserDAO {

    @Inject
    private EntityManager em;

	public List<User> getAll() {
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public User findById(String id) {
		return em.find(User.class, id);
	}

	public void update(User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();

	}

	public void create(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void delete(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
