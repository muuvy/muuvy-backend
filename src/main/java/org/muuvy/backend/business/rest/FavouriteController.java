package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.FavouriteDto;
import org.muuvy.backend.business.services.FavouriteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/user/{userId}/favourite")
@Produces("application/json")
public class FavouriteController {
	private static final Logger LOG = Logger.getLogger(FavouriteController.class);

	@Inject
	private FavouriteService favouriteService;

	@PathParam("userId")
	private String userId;

	@GET
	public Response getFavorites(@Context HttpHeaders headers) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getFavorites. useragent %s", userAgent));
			List<FavouriteDto> favourites = favouriteService.getFavouritesByUserId(userId);
			return Response.ok(favourites).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in getUsers. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@POST
	public Response createFavourite(@Context HttpHeaders headers, FavouriteDto favourite) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getUsers. useragent %s", userAgent));
			favouriteService.createFavourite(favourite);
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
			favouriteService.deleteFavouriteId(movieId);
			return Response.ok(String.format("deleted %s", movieId)).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in deleteFavourite. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}
}
