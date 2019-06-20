package org.muuvy.backend.business.rest;


import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
public class UserController {

	@Inject
	private UserService userService;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from userController!").build();
	}

	@POST
	public Response createUser(UserDto user){
		userService.createUser(user);
		return Response.ok().build();
	}

	@DELETE
	public Response deleteUser(@QueryParam("userId") String userId){
		userService.deleteById(userId);
		return Response.ok(String.format("deleted %s", userId)).build();
	}
}
