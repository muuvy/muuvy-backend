package org.muuvy.backend.hello;

import org.muuvy.backend.dto.FavouriteDto;
import org.muuvy.backend.services.FavouriteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;



@Path("/user/{userId}/favourite")
public class FavouriteController {
    @Inject
    private FavouriteService favouriteService;

    @PathParam("userId")
    private String userId;


    @GET
    @Produces("text/plain")
    public Response doGet(){
        return Response.ok(String.format("Hello %s from FavouriteService", userId)).build();
    }

    @POST
    public Response createFavourite(FavouriteDto favourite){
       favouriteService.createFavourite(favourite);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteFavourite(@QueryParam("movieId") String movieId){
       favouriteService.deleteFavouriteId(movieId);
        return Response.ok().build();
    }
}
