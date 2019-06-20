package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService {
	private static final String API_KEY = "089c6c8c71abaa1a9d5355db386f032b";

	@Inject
	private UserDAO userDAO;

	public UserDto createUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getFullName(), API_KEY);
		userDAO.create(user);
		return userDto;
	}

	public void deleteById(String id) {
		User getUser = userDAO.findById(id);
		userDAO.delete(getUser);
	}

	public UserDto getUser(String id) {
		User user = userDAO.findById(id);
		return new UserDto(user.getId(), user.getFullName());
	}
}
