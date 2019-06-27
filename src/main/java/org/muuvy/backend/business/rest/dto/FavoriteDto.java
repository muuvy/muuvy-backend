package org.muuvy.backend.business.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.muuvy.backend.persistence.models.Favorite;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private String id;
    private String movieId;

    public FavoriteDto(Favorite favorite) {
        this.id = favorite.getId();
        this.movieId = favorite.getMovieId();
    }

    public Favorite toModel() {
        Favorite favorite = new Favorite();
        favorite.setId(this.id);
        favorite.setMovieId(this.movieId);
        return favorite;
    }

}
