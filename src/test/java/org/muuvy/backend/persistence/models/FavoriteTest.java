package org.muuvy.backend.persistence.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FavoriteTest {

	@Test
	public void createFavoriteTest() {
		Favorite favorite = new Favorite("movie123", "user123");
		assertNotNull(favorite);
		assertEquals("movie123", favorite.getId());
	}
}