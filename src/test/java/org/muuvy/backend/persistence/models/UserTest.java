package org.muuvy.backend.persistence.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void CreateFavoriteTest(){
        User user = new User();
        assertNotNull(user);
    }

}