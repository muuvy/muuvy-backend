package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.business.rest.dto.FavouriteDto;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FavouriteService {

	@Inject
	private FavoriteDAO favoriteDAO;

	public void createFavourite(FavouriteDto favouriteDto) {
		Favorite favorite = new Favorite(favouriteDto.getId(), favouriteDto.getUserId());
		favoriteDAO.create(favorite);
	}

	public void deleteFavouriteId(String movieId) {
		Favorite getFavorite = favoriteDAO.findById(movieId);
		favoriteDAO.delete(getFavorite);
	}

	public List<FavouriteDto> getFavouritesByUserId(String userId) {
		List<Favorite> favorites = favoriteDAO.getAll();
		return favorites.stream().filter(fM -> fM.getUserId().equals(userId))
				.map(f -> new FavouriteDto(f.getId(), f.getUserId())).collect(Collectors.toList());
	}
}
