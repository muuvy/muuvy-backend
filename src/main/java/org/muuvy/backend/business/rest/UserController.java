package org.muuvy.backend.business.rest;


import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.UserService;
import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Inject
	private UserService userService;

	@GET
	@Produces("text/plain")

	public Response doGet() {
		try{
			LOG.info(doGet());
			return Response.ok("Hello from userController!").build();
		}catch (Exception e){
			LOG.error(doGet());
			return Response.serverError().build();
		}
	}

	@POST
	public Response createUser(UserDto user){
		try{
			userService.createUser(user);
			LOG.info(user);
			return Response.ok().build();
		}catch (Exception e){
			LOG.error(user);
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteUser(@QueryParam("userId") String userId){
		try{
			userService.deleteById(userId);
			LOG.info(userId);
			return Response.ok(String.format("deleted %s", userId)).build();
		}catch (Exception e){
			LOG.error(userId);
			return Response.serverError().build();
		}
	}
}
