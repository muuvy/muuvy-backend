package org.muuvy.backend.business.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceIT {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserInDb(){
        String userId = "1234";

        Set<Favorite> favorites = new HashSet();
        favorites.add(new Favorite("movie1"));
        favorites.add(new Favorite("movie2"));
        favorites.add(new Favorite("movie3"));
        favorites.add(new Favorite("movie4"));
        UserDto user = new UserDto(new User(userId, "michi", "", favorites));

        Mockito.when(userDAO.create(Mockito.any(User.class))).thenReturn(user.toModel());
        Mockito.when(userDAO.findByName(user.getFullName())).thenReturn(null);

        assertEquals(userService.createUser(user).getFullName(), user.getFullName());
        assertEquals(userService.createUser(user).getFavorites().size(), user.getFavorites().size());

    }

    @Test
    public void testGetAllUsers(){

        String userId = "1234";

        Set<Favorite> favorites = new HashSet<>();
        favorites.add(new Favorite("movie1"));
        favorites.add(new Favorite("movie2"));

        UserDto user = new UserDto(new User(userId, "michi", "", favorites));

        List<User> users = new ArrayList<>();
        users.add(user.toModel());
        users.add(user.toModel());

        Mockito.when(userDAO.getAll()).thenReturn(users);

        assertEquals(userService.getUsers().size(), users.size());

    }
}
