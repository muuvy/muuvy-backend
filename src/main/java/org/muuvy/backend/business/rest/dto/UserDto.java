package org.muuvy.backend.business.rest.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String id;
	private String fullName;
	private String apiKey;
	private Set<FavoriteDto> favorites;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Set<FavoriteDto> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<FavoriteDto> favorites) {
		this.favorites = favorites;
	}
}
