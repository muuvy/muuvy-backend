package org.muuvy.backend.business.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.muuvy.backend.persistence.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

 @Mock
 private static EntityManager entityManager;

 @Mock
 private static Query query;

 @Mock
 private static User user;


    @Test
    void createUser() {
        User user = new User();
        assertNotNull(user);

    }

    @Test
    void deleteById() {

    }

    @Test
    void getUser() {
    }
}
