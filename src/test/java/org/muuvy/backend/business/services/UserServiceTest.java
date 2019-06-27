package org.muuvy.backend.business.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserServiceTest {

 @Mock
 private static EntityManager entityManager;

 @Mock
 private static Query query;

 @Mock
 private static User  user;
 private static UserDAO userDAO;

 @Mock
 private static UserDto userDto;

    @Test
    void createUser() {
     user = new User();
     assertNotNull(user);
    }

    @Test
    void deleteById() {
        user = new User();

    }

    @Test
    void getUser() {

    }
}
