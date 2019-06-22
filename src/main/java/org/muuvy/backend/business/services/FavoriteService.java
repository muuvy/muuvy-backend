package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class FavoriteService {

	@Inject
	private FavoriteDAO favoriteDAO;

	public void createFavorite(FavoriteDto favoriteDto) {
		Favorite favorite = new Favorite(favoriteDto.getId(), favoriteDto.getUserId());
		favoriteDAO.create(favorite);
	}

	public void deleteFavoriteId(String movieId, String userId) {
		List<Favorite> favorites = favoriteDAO.getAll();
		Optional<Favorite> favorite = favorites.stream()
				.filter(fM -> fM.getUserId().equals(userId) && fM.getId().equals(movieId)).findFirst();
		if (favorite.isPresent()) {
			favoriteDAO.delete(favorite.get());
		}
	}

	public List<FavoriteDto> getFavoritesByUserId(String userId) {
		List<Favorite> favorites = favoriteDAO.getAll();
		return favorites.stream().filter(fM -> fM.getUserId().equals(userId))
				.map(f -> new FavoriteDto(f.getId(), f.getUserId())).collect(Collectors.toList());
	}
}
