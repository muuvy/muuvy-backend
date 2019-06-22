package org.muuvy.backend.persistence.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "User.findAll", query = "SELECT t FROM User t")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
	@Id
	private String id;
	private String fullName;
	private String apiKey;

	@OneToMany
	private Set<Favorite> favorites;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

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

	public Set<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}
}
