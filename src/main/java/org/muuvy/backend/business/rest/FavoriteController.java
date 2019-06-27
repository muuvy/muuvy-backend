package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.FavoriteDto;
import org.muuvy.backend.business.services.FavoriteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user/{userId}/favourite")
@Produces("application/json")
@ApplicationScoped
public class FavoriteController {
	private static final Logger LOG = Logger.getLogger(FavoriteController.class);

	@Inject
	private FavoriteService favoriteService;

	@PathParam("userId")
	private String userId;

	@GET
	public Response getFavorites(@Context HttpHeaders headers) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getFavorites. useragent %s", userAgent));
			List<FavoriteDto> favourites = favoriteService.getFavoritesByUserId(userId);
			return Response.ok(favourites).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in getUsers. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@POST
	public Response createFavorite(@Context HttpHeaders headers, FavoriteDto favourite) {
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
	public Response deleteFavorite(@Context HttpHeaders headers, @QueryParam("movieId") String movieId) {
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
