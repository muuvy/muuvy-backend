package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.business.rest.dto.FavouriteDto;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FavouriteService {
    @Inject
    private FavoriteDAO favoriteDAO;

    public void createFavorite(FavouriteDto favouriteDto) {
        Favorite favorite = new Favorite(favouriteDto.getId());
        favoriteDAO.create(favorite);
    }


    public void deleteFavoriteId(String movieId) {
        Favorite getFavorite = favoriteDAO.findById(movieId);
        favoriteDAO.delete(getFavorite);
    }

    public FavouriteDto getFavotire(FavouriteDto favourite){

        return favourite;
    }


}
