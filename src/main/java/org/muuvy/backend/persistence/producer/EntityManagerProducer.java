package org.muuvy.backend.persistence.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	@Produces
	@ApplicationScoped
	public EntityManager createEntityManager() {
		return Persistence
				.createEntityManagerFactory("muuvy")
				.createEntityManager();
	}

	public void close(EntityManager entityManager) {
		entityManager.close();
	}

}