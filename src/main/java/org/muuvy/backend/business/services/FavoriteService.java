package org.muuvy.backend.business.services;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.persistence.models.Favorite;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FavoriteService {

	@Inject
	private FavoriteDAO favoriteDAO;

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserService userService;

	private static final Logger LOG = Logger.getLogger(FavoriteService.class);

	public List<FavoriteDto> getAllFavorites() {

		List<Favorite> favorites = favoriteDAO.getAll();
		List<FavoriteDto> favoriteDtos = new ArrayList<>();

		for (Favorite favorite : favorites) {
			FavoriteDto favoriteDto = new FavoriteDto(favorite.getId(), favorite.getMovieId());
			favoriteDtos.add(favoriteDto);
		}
		return favoriteDtos;
	}

	public List<FavoriteDto> getAllFavoritesByUser(String userId) {

		UserDto user = userService.getUser(userId);
		List<FavoriteDto> favoriteDtos = new ArrayList<>();
		user.getFavorites().forEach(fav -> favoriteDtos.add(new FavoriteDto(fav.getId(), fav.getMovieId())));
		return favoriteDtos;
	}


	public void deleteFavoriteByMovieId(String userId, String movieId) {
		LOG.infov("remove fav {0} from user {1}", movieId, userId);
		Optional<User> user = userDAO.findById(userId);

		if (user.isPresent()) {
			Favorite favToDelete = user.get().getFavourite(movieId);
			user.get().removeFavorite(movieId);
			userDAO.update(user.get());
			LOG.infov("updated delete changes on user");

			LOG.infov("removed fav from user object {0}", user.toString());
			favoriteDAO.delete(favToDelete);
			LOG.infov("removed fav from database");
		}

	}
}
