package org.muuvy.backend.business.rest;

import org.muuvy.backend.business.dao.UserDAO;
import org.muuvy.backend.persistence.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@ApplicationScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {

    @Inject
    private UserDAO userDAO;

    @GET
    public Response getAll() {
        return Response.ok(userDAO.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") String id) {
        User user = userDAO.findById(id);

        return Response.ok(user).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, User user) {
        User updateUser = userDAO.findById(id);

        updateUser.setName(user.getName());
        userDAO.update(updateUser);

        return Response.ok().build();
    }

    @POST
    public Response create(User user) {
        userDAO.create(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        User getUser = userDAO.findById(id);

        userDAO.delete(getUser);

        return Response.ok().build();
    }
}
