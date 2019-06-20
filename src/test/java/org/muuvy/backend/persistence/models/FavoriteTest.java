package org.muuvy.backend.persistence.models;

import org.junit.jupiter.api.Test;
import org.muuvy.backend.business.dao.FavoriteDAO;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FavoriteTest {

	@Test
	public void createFavoriteTest() {
		FavoriteDAO favoriteDAO = new FavoriteDAO();
		favoriteDAO.create(new Favorite("movie123"));
		assertNotNull(favoriteDAO.getAll());
	}

}