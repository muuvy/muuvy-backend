package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.FavouriteDto;
import org.muuvy.backend.business.services.FavouriteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user/{userId}/favourite")
public class FavouriteController {
	private static final Logger LOG = Logger.getLogger(FavouriteController.class);

	@Inject
	private FavouriteService favouriteService;

	@PathParam("userId")
	private String userId;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		try {
			LOG.info(doGet());
			return Response.ok(String.format("Hello %s from FavouriteService", userId)).build();
		} catch (Exception e) {
			LOG.error(doGet());
			return Response.serverError().build();
		}
	}

	@POST
	public Response createFavourite(FavouriteDto favourite) {
		try {
			favouriteService.createFavourite(favourite);
			LOG.info(favourite);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error(favourite);
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteFavourite(@QueryParam("movieId") String movieId) {
		try {
			favouriteService.deleteFavouriteId(movieId);
			LOG.info(movieId);
			return Response.ok(String.format("deleted %s", movieId)).build();
		} catch (Exception e) {
			LOG.error(movieId);
			return Response.serverError().build();
		}
	}
}
