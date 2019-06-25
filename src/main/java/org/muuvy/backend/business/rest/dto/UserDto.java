package org.muuvy.backend.business.rest.dto;

import java.util.Set;

import lombok.*;
import org.muuvy.backend.persistence.models.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

	private String id;

	@NonNull
	private String fullName;
	private String apiKey;

	private Set<FavoriteDto> favorites;

	public UserDto(User user) {
		this.fullName = user.getFullName();
		this.id = user.getId();
		this.apiKey = user.getApiKey();
	}

	public FavoriteDto addFavorite(FavoriteDto favoriteDto) {
		favorites.add(favoriteDto);
		return favoriteDto;
	}

	public void removeFavorite(FavoriteDto favoriteDto) {
		favorites.remove(favoriteDto);
	}


}
