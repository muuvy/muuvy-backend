package org.muuvy.backend.persistence.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.persistence.producer.EntityManagerProducer;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteTest {

    private static EntityManager entityManager;

    @BeforeAll
    public static void setup(){
        EntityManagerProducer entityManagerProducer = new EntityManagerProducer();
        entityManager = entityManagerProducer.createEntityManager();
    }

    @Test
    public void createFavoriteTest(){
        FavoriteDAO favoriteDAO = new FavoriteDAO();
        favoriteDAO.setEntityManager(entityManager);
        favoriteDAO.create(new Favorite("movie123"));
        assertNotNull(favoriteDAO.getAll());
    }

}