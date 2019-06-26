package org.muuvy.backend.business.services;

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
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setApiKey(API_KEY);
		user.setFavorites(new HashSet<>());
		return new UserDto(userDAO.create(user));
	}

	public UserDto login(UserDto userDto) {

		// Check if the user exists
		Optional<User> userOptional = userDAO.findByName(userDto.getFullName());

		if (userOptional.isPresent()) {
			// take the first found user
			User u = userOptional.get();
			return new UserDto(u);
		} else {
			User user = new User();
			user.setFullName(userDto.getFullName());
			user.setApiKey(API_KEY);
			user.setFavorites(new HashSet<>());
			return this.createUser(userDto);
		}
	}

	public void deleteById(String id) {
		User getUser = userDAO.findById(id);
		userDAO.delete(getUser);
	}

	public UserDto getUser(String userId) {

		User user = userDAO.findById(userId);
		LOG.debugv("Get one user by its id {} was {}", userId, user);

		Set<FavoriteDto> favorites = user.getFavorites().stream().map(f -> new FavoriteDto(f.getId(), f.getMovieId()))
				.collect(Collectors.toSet());

		return new UserDto(user.getId(), user.getFullName(), user.getApiKey(), favorites);
	}

	public List<UserDto> getUsersByName(String userName) {

		Optional<User> optionalUser = userDAO.findByName(userName);

		List<UserDto> users = new ArrayList<UserDto>();

		if (optionalUser.isPresent()) {
			users.add(new UserDto(optionalUser.get()));
		}
		return users;
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

		for (FavoriteDto fd : userDto.getFavorites()) {
			favorites.add(new Favorite(fd.getId(), fd.getMovieId()));
		}

		User user = new User(userDto.getId(), userDto.getFullName(), userDto.getApiKey(), favorites);

		userDAO.update(user);

		return userDto;
	}
}
