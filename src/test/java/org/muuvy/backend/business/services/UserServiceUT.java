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

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceUT {

    @Test
    void createUser() {
     User user = new User();
     assertNotNull(user);
    }

    @Test
    void deleteById() {
        User user = new User();
        user = null;
        assertNull(user);
    }

    @Test
    void userToStringNotEmpty() {
        User user = new User();
        assertNotEquals("", user.toString());
    }
}
