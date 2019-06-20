package org.muuvy.backend.services;

import org.muuvy.backend.dto.FavouriteDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FavouriteService {

    public FavouriteDto createFavourite(FavouriteDto favourite) {

        return favourite;
    }


    public void deleteFavouriteId(String movieId) {

    }

    public FavouriteDto getFavoutire(FavouriteDto favourite){

        return favourite;
    }


}
