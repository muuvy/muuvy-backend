package org.muuvy.backend.business.services;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.Favorite;
import org.muuvy.backend.persistence.models.User;

@ApplicationScoped
public class UserService {

	private static final String API_KEY = "089c6c8c71abaa1a9d5355db386f032b";
	private static final Logger LOG = Logger.getLogger(UserService.class);

	@Inject
	private UserDAO userDAO;

	public UserDto createUser(UserDto userDto) {

		// Check if the user already exists
		if (!userExists(userDto.getFullName())) {

			User user = new User();

			user.setFullName(userDto.getFullName());
			user.setApiKey(API_KEY);

			if (userDto.getFavorites() != null && userDto.getFavorites().size() > 0) {
				Set<Favorite> favorites = new HashSet<>();
				for (FavoriteDto favoriteDto : userDto.getFavorites()) {
					favorites.add(new Favorite(favoriteDto.getId(), favoriteDto.getMovieId()));
				}
				user.setFavorites(favorites);

			} else {
				user.setFavorites(new HashSet<>());
			}

			// create new user
			return new UserDto(userDAO.create(user));

		} else {

			// return existing user
			return new UserDto(userDAO.findByName(userDto.getFullName()));
		}
	}

	public UserDto login(UserDto userDto) {
		// Check if the user exists
		User userFound = userDAO.findByName(userDto.getFullName());
		LOG.infov("found {0} users in database for {1}", userFound, userDto.getFullName());

		if (userFound != null) {
			// take the first found user
			return new UserDto(userFound);
		} else {
			User user = new User();
			user.setFullName(userDto.getFullName());
			user.setApiKey(API_KEY);
			user.setFavorites(new HashSet<>());
			return this.createUser(userDto);
		}
	}

	public void deleteById(String id) {
		Optional<User> user = userDAO.findById(id);
		user.ifPresent(value -> userDAO.delete(value));
	}

	public UserDto getUser(String userId) {

		Optional<User> user = userDAO.findById(userId);
		LOG.debugv("Get one user by its id {} was {}", userId, user);

		if (user.isPresent()) {
			Set<FavoriteDto> favorites = user.get().getFavorites().stream().map(f -> new FavoriteDto(f.getId(),
					f.getMovieId()))
					.collect(Collectors.toSet());
			return new UserDto(user.get(), favorites);
		} else {
			// TODO: Implement Error Handling
			return null;
		}

	}

	public UserDto getUsersByName(String userName) {
		User user = userDAO.findByName(userName);

		if (user != null) {
			return new UserDto(user);
		} else {
			return null;
		}
	}

	public List<UserDto> getUsers() {
		List<User> users = userDAO.getAll();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (User user : users) {
			Set<FavoriteDto> favorites = user.getFavorites().stream()
					.map(f -> new FavoriteDto(f.getId(), f.getMovieId())).collect(Collectors.toSet());
			UserDto userDto = new UserDto(user.getId(), user.getFullName(), user.getApiKey(), favorites);
			userDtos.add(userDto);
		}
		return userDtos;
	}

	public UserDto updateUser(UserDto userDto, String userId) {

		Set<Favorite> favorites = new HashSet<>();


		LOG.infov("Update the user including favorites count {0}", userDto.toString());

		for(FavoriteDto fd : userDto.getFavorites()) {
			favorites.add(new Favorite(fd.getId(), fd.getMovieId()));
		}

		User user = new User(userId, userDto.getFullName(), userDto.getApiKey(), favorites);
		userDAO.update(user);

		return userDto;
	}

	public Boolean userExists(String fullName) {
		User user = userDAO.findByName(fullName);
		return user != null;
	}
}
