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

<<<<<<< HEAD
    public void createFavorite(FavouriteDto favouriteDto) {
        Favorite favorite = new Favorite(favouriteDto.getId());
        favoriteDAO.create(favorite);
    }
=======
	@Inject
	private FavoriteDAO favoriteDAO;
>>>>>>> 116c386dc408c6732a319e276955826958e218fc

	public void createFavourite(FavouriteDto favouriteDto) {
		Favorite favorite = new Favorite(favouriteDto.getId(), favouriteDto.getUserId());
		favoriteDAO.create(favorite);
	}

<<<<<<< HEAD
    public void deleteFavoriteId(String movieId) {
        Favorite getFavorite = favoriteDAO.findById(movieId);
        favoriteDAO.delete(getFavorite);
    }

    public FavouriteDto getFavotire(FavouriteDto favourite){

        return favourite;
    }

=======
	public void deleteFavouriteId(String movieId) {
		Favorite getFavorite = favoriteDAO.findById(movieId);
		favoriteDAO.delete(getFavorite);
	}
>>>>>>> 116c386dc408c6732a319e276955826958e218fc

	public List<FavouriteDto> getFavouritesByUserId(String userId) {
		List<Favorite> favorites = favoriteDAO.getAll();
		return favorites.stream().filter(fM -> fM.getUserId().equals(userId))
				.map(f -> new FavouriteDto(f.getId(), f.getUserId())).collect(Collectors.toList());
	}
}
