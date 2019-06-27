package org.muuvy.backend.business.rest;


import org.jboss.logging.Logger;
import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.FavoriteService;
import org.muuvy.backend.business.services.UserService;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@ApplicationScoped
public class FavoriteController {

	private static final Logger LOG = Logger.getLogger(FavoriteController.class);

	@Inject
	private FavoriteService favoriteService;

	@Inject
	private UserService userService;


	@GET
	@Path("/users/{userId}/favourites")
	public Response getFavoritesByUserId(@Context HttpHeaders headers, @PathParam("userId") String userId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start getFavorites. useragent {0}", userAgent);
			UserDto user = userService.getUser(userId);
			return Response.ok(user.getFavorites()).build();
		} catch (Exception e) {
			LOG.errorv("Error in getUsers. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}


	@POST
	@Path("/users/{userId}/favourites")
	public Response createFavourite(@Context HttpHeaders headers, @PathParam("userId") String userId, FavoriteDto favourite) {
		try {

			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start getUsers. useragent {0}", userAgent);

			UserDto userDto = userService.getUser(userId);

			if (userDto != null) {
				LOG.infov("user {0} found", userDto.getFullName());

				if (!userDto.toModel().favoriteExists(favourite.getMovieId())) {
					LOG.infov("the favourite does not exist");

					User user = userDto.toModel();
					user.addFavorite(favourite);
					LOG.infov("Adding favs {0} to user {1}", user.getFavorites().size(), user.toString());
					userService.updateUser(new UserDto(user), user.getId());

					return Response.noContent().entity(favourite).build();
				} else {
					LOG.infov("the favourite already exists");
					return Response.noContent().entity("already exists").build();
				}

			} else {
				LOG.infov("the favourite already exists");
				return Response.status(Response.Status.NOT_FOUND).entity("User with id " + userId + " not found").build();
			}

		} catch (Exception e) {
			LOG.error("Error in createFavourite. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("/users/{userId}/favourites/{movieId}")
	public Response deleteFavourite(@Context HttpHeaders headers,@PathParam("userId") String userId, @PathParam("movieId") String movieId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start getUsers. useragent {0}", userAgent);
			favoriteService.deleteFavoriteByMovieId(userId, movieId);
			return Response.noContent().build();
		} catch (Exception e) {
			LOG.errorv("Error in deleteFavourite. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}
}
