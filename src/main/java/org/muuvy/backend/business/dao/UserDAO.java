package org.muuvy.backend.business.dao;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.User;

@ApplicationScoped
public class UserDAO {

	@Inject
	private EntityManagerProducer emProducer;

	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	public List<User> getAll() {
		EntityManager em = emProducer.createEntityManager();
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public User findById(String id) {
		EntityManager em = emProducer.createEntityManager();
		return em.find(User.class, id);
	}

	public Optional<User> findByName(String name) {
		EntityManager em = emProducer.createEntityManager();
		String query = "{ $query : { fullName : '" + name + "' } }";
		try{
			User user = (User) em.createNativeQuery(query, User.class).getSingleResult();
			return Optional.of(user);
		}
		catch(NoResultException noResEx){
			return Optional.empty();
		}
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
