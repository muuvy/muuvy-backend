package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.FavoriteService;
import org.muuvy.backend.business.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/user/{userId}/favourite")
@Produces("application/json")
@ApplicationScoped
public class FavoriteController {
	private static final Logger LOG = Logger.getLogger(FavoriteController.class);

	@Inject
	private FavoriteService favoriteService;

	@Inject
	private UserService userService;

	@PathParam("userId")
	private String userId;

	@GET
	public Response getFavorites(@Context HttpHeaders headers) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getFavorites. useragent %s", userAgent));
			UserDto user = userService.getUser(userId);
			return Response.ok(user.getFavorites()).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in getUsers. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@POST
	public Response createFavourite(@Context HttpHeaders headers, FavoriteDto favourite) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getUsers. useragent %s", userAgent));
			favoriteService.createFavorite(favourite);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error(String.format("Error in createFavourite. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteFavourite(@Context HttpHeaders headers, @QueryParam("movieId") String movieId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getUsers. useragent %s", userAgent));
			favoriteService.deleteFavoriteId(movieId, userId);
			return Response.ok(String.format("deleted %s", movieId)).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in deleteFavourite. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}
}
