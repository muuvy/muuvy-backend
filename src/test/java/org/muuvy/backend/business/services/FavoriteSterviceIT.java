package org.muuvy.backend.business.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteSterviceIT {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFavoriteInDB(){
        String userId = "1234";

        Set<Favorite> favorites = new HashSet();
        favorites.add(new Favorite("movie1"));
        favorites.add(new Favorite("movie2"));
        favorites.add(new Favorite("movie3"));
        favorites.add(new Favorite("movie4"));
        UserDto user = new UserDto(new User(userId, "michi", "", favorites));

        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(user.toModel()));

        List<Favorite> expectedList = new ArrayList(favorites);
        List<FavoriteDto> listToCompare = new ArrayList(userService.getUser(userId).getFavorites());



        assert expectedList.retainAll( listToCompare );


    }
}
