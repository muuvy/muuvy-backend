package org.muuvy.backend.business.rest.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jboss.logging.Logger;
import org.muuvy.backend.business.services.UserService;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

	@JsonIgnore
	@Transient
	private static final Logger LOG = Logger.getLogger(UserService.class);

	private String id;

	@NonNull
	private String fullName;
	private String apiKey;

	private Set<FavoriteDto> favorites;

	public UserDto(User user) {
		this.fullName = user.getFullName();
		this.id = user.getId();
		this.apiKey = user.getApiKey();
		this.favorites = new HashSet<>();

		LOG.infov("Create User DTO from User {0}", user.toString());

		for (Favorite fav : user.getFavorites()) {
			LOG.infov("Add Favorite {0} to user {1}", fav.toString(), user.toString());
			this.favorites.add(new FavoriteDto(fav));
		}

		LOG.infov("Created user dto {0}", this.toString());
	}

	public UserDto(User user, Set<FavoriteDto> favorites) {
		this.fullName = user.getFullName();
		this.id = user.getId();
		this.apiKey = user.getApiKey();
		this.favorites = favorites;
	}

	public User toModel() {

		User user = new User();
		user.setApiKey(this.apiKey);
		user.setId(this.id);

		Set<Favorite> favorites = new HashSet<>();

		for(FavoriteDto favoriteDto : this.favorites) {
			favorites.add(favoriteDto.toModel());
		}

		user.setFavorites(favorites);
		user.setFullName(this.fullName);

		return user;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"id='" + id + '\'' +
				", fullName='" + fullName + '\'' +
				", apiKey='" + apiKey + '\'' +
				", favorites=" + favorites +
				'}';
	}

}
