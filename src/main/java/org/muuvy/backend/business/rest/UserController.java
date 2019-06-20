package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces("application/json")
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Inject
	private UserService userService;

	@GET
	public Response getUsers(@Context HttpHeaders headers) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start getUsers. useragent %s", userAgent));
			return Response.ok(userService.getUsers()).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in getUsers. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@POST
	public Response createUser(@Context HttpHeaders headers, UserDto user) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start createUser. useragent %s", userAgent));
			userService.createUser(user);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error(String.format("Error in createUser. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteUser(@Context HttpHeaders headers, @QueryParam("userId") String userId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.info(String.format("start deleteUser. useragent %s", userAgent));
			userService.deleteById(userId);
			LOG.info(userId);
			return Response.ok(String.format("deleted %s", userId)).build();
		} catch (Exception e) {
			LOG.error(String.format("Error in deleteUser. Message %s", e.getMessage()), e);
			return Response.serverError().build();
		}
	}
}
