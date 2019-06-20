package org.muuvy.backend.business.rest;

import org.muuvy.backend.business.dao.FavoriteDAO;
import org.muuvy.backend.persistence.models.Favorite;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FavoriteEndpoint {

    @Inject
    private FavoriteDAO favoriteDAO;

    @GET
    public Response getAll() {
        return Response.ok(favoriteDAO.getAll()).build();
    }

    @POST
    public Response create(Favorite favorite) {
        favoriteDAO.create(favorite);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        Favorite getFavorite = favoriteDAO.findById(id);
        favoriteDAO.delete(getFavorite);

        return Response.ok().build();
    }
}
