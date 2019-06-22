package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
	private static final String API_KEY = "089c6c8c71abaa1a9d5355db386f032b";

	@Inject
	private UserDAO userDAO;

	public UserDto createUser(UserDto userDto) {
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setApiKey(API_KEY);
		user.setFavorites(new HashSet<Favorite>());
		userDAO.create(user);
		return userDto;
	}

	public void deleteById(String id) {
		User getUser = userDAO.findById(id);
		userDAO.delete(getUser);
	}

	public UserDto getUser(String id) {
		User user = userDAO.findById(id);
		Set<FavoriteDto> favorites = user.getFavorites().stream().map(f -> new FavoriteDto(f.getId(), f.getMovieId()))
				.collect(Collectors.toSet());
		return new UserDto(user.getId(), user.getFullName(), user.getApiKey(), favorites);
	}

	public List<UserDto> getUsers() {
		List<User> users = userDAO.getAll();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (User user : users) {
			Set<FavoriteDto> favorites = user.getFavorites().stream().map(f -> new FavoriteDto(f.getId(), f.getMovieId()))
					.collect(Collectors.toSet());
			UserDto userDto = new UserDto(user.getId(), user.getFullName(), user.getApiKey(), favorites);
			userDtos.add(userDto);
		}
		return userDtos;
	}
}
