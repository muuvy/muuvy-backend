package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FavoriteService {

	@Inject
	private FavoriteDAO favoriteDAO;

	public void createFavorite(FavoriteDto favoriteDto) {
		Favorite favorite = new Favorite(favoriteDto.getMovieId());
		favoriteDAO.create(favorite);
	}

	public void deleteFavoriteId(String id, String movieId) {
		List<Favorite> favorites = favoriteDAO.getAll();
		Optional<Favorite> favorite = favorites.stream()
				.filter(fM -> fM.getId().equals(movieId) && fM.getId().equals(movieId)).findFirst();
		if (favorite.isPresent()) {
			favoriteDAO.delete(favorite.get());
		}
	}
}
