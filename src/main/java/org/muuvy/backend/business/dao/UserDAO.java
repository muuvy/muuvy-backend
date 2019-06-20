package org.muuvy.backend.business.dao;

import org.muuvy.backend.business.rest.RestApplication;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class UserDAO {

	public List<User> getAll() {
		EntityManager em = RestApplication.getEntityManager();
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public User findById(String id) {
		EntityManager em = RestApplication.getEntityManager();
		return em.find(User.class, id);
	}

	public void update(User user) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();

	}

	public void create(User user) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void delete(User user) {
		EntityManager em = RestApplication.getEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
