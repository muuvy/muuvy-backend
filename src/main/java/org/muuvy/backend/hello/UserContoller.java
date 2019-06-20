package org.muuvy.backend.hello;


import org.muuvy.backend.dto.UserDto;
import org.muuvy.backend.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
public class UserContoller {

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
		return Response.ok().build();
	}
}
