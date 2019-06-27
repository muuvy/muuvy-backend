package org.muuvy.backend.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.*;

import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.muuvy.backend.business.rest.dto.FavoriteDto;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "User.findAll", query = "SELECT t FROM User t")
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fullName;
	private String apiKey;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Favorite> favorites;

	public FavoriteDto addFavorite(FavoriteDto favoriteDto) {
		favorites.add(favoriteDto.toModel());
		return favoriteDto;
	}

	public void removeFavorite(FavoriteDto favoriteDto) {
		favorites.remove(favoriteDto);
	}

	public void removeFavorite(String movieId) {
		for (Favorite fav : favorites) {
			if (fav.getMovieId().equals(movieId)) {
				this.favorites.remove(fav);
			}
		}
	}

	public Favorite getFavourite(String movieId) {
	    for (Favorite fav : favorites) {
	        if (fav.getMovieId().equals(movieId)) {
	            return fav;
            }
        }
	    return null;
    }

	public Boolean favoriteExists(String movieId) {
		for (Favorite fav : this.getFavorites()) {
			if (fav.getMovieId().equals(movieId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", fullName='" + fullName + '\'' +
				", apiKey='" + apiKey + '\'' +
				", favorites=" + favorites +
				'}';
	}
}
