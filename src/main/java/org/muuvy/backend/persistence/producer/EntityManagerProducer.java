package org.muuvy.backend.persistence.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("restapi-unit")
                .createEntityManager();
    }

    public void close(EntityManager entityManager) {
        entityManager.close();
    }

}