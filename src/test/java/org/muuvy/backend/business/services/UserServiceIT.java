package org.muuvy.backend.business.services;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.EntityManagerProducer;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceIT {

    @Mock
    private EntityManagerProducer emProd;

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {

        userService = new UserService();
        emProd = new EntityManagerProducer();
        userDAO = new UserDAO();
        userDAO.emProducer = emProd;
        userService.userDAO = userDAO;
    }

    @Test
    public void createUserInDb(){
        String userId = "1234";

        Set<Favorite> favorites = new HashSet();
        favorites.add(new Favorite("movie1"));
        favorites.add(new Favorite("movie2"));
        favorites.add(new Favorite("movie3"));
        favorites.add(new Favorite("movie4"));
        UserDto user = new UserDto(new User(userId, "michi", "", favorites));
        userService.createUser(user);

        assertEquals(userService.getUser("userId").getId(), userId);
    }

    @Test
     void getUsersFormDB(){
        userService.getUsers();


    }

}
