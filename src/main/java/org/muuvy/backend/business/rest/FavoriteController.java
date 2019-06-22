package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.FavouriteDto;
import org.muuvy.backend.business.services.FavouriteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/user/{userId}/favourite")
<<<<<<< HEAD:src/main/java/org/muuvy/backend/business/rest/FavoriteController.java
public class FavoriteController {
    private static final Logger LOG = Logger.getLogger(FavoriteController.class);


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
        }catch (Exception e){
            LOG.error(doGet());
            return Response.serverError().build();
        }

    }


    @POST
    public Response createFavorite(FavouriteDto favourite){
        try {
            favouriteService.createFavorite(favourite);
            LOG.info(favourite);
            return Response.ok().build();
        }catch (Exception e){
           LOG.error(favourite);
           return Response.serverError().build();
        }
    }

    @DELETE
    public Response deleteFavorite(@QueryParam("movieId") String movieId){
        try {
            favouriteService.deleteFavoriteId(movieId);
            LOG.info(movieId);
            return Response.ok(String.format("deleted %s", movieId)).build();
        }catch (Exception e){
            LOG.error(movieId);
            return Response.serverError().build();
        }

    }
=======
@Produces("application/json")
@ApplicationScoped
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
>>>>>>> 116c386dc408c6732a319e276955826958e218fc:src/main/java/org/muuvy/backend/business/rest/FavouriteController.java
}
