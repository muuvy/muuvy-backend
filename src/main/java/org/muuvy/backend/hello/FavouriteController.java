package org.muuvy.backend.hello;

import org.muuvy.backend.dto.FavouriteDto;
import org.muuvy.backend.services.FavouriteService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;



@Path("/api")
public class FavouriteController {

    private FavouriteService favouriteService;


    @GET
    @Produces("text/plain")
    public Response doGet(){
        return Response.ok("Hello from FavouriteService").build();
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
