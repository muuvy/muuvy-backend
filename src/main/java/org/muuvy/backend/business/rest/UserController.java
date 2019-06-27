package org.muuvy.backend.business.rest;

import org.jboss.logging.Logger;
import org.muuvy.backend.business.rest.dto.UserDto;
import org.muuvy.backend.business.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@ApplicationScoped
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Inject
	private UserService userService;


	@POST
	@Path("/login")
	public Response loginUser(@Context HttpHeaders headers, UserDto user) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start createUser. useragent {0}", userAgent);
			return Response.ok(userService.login(user)).build();
		} catch (Exception e) {
			LOG.errorv("Error in createUser. Message %s", e.getMessage(), e);
			return Response.serverError().build();
		}
	}


	@GET
	@Path("/users")
	public Response getUsers(@Context HttpHeaders headers) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start getUsers. useragent {0}", userAgent);
			return Response.ok(userService.getUsers()).build();
		} catch (Exception e) {
			LOG.errorv("Error in getUsers. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/users/search/{userName}")
	public Response searchUserByName(@Context HttpHeaders headers, @PathParam("userName") String userName) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start search users. useragent %s", userAgent);
			return Response.ok(userService.getUsersByName(userName)).build();
		} catch (Exception e) {
			LOG.errorv("Error in getUsers. Message %s", e.getMessage(), e);
			return Response.serverError().build();
		}
	}


	@GET
	@Path("/users/{userId}")
	public Response getUser(@Context HttpHeaders headers, @PathParam("userId") String userId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.debugv("start getUser. useragent {0}", userAgent);
			LOG.infov("start getUser. useragent {0}", userAgent);
			return Response.ok(userService.getUser(userId)).build();
		} catch (Exception e) {
			LOG.errorv("Error in getUsers. Message %s", e.getMessage(), e);
			return Response.serverError().build();
		}
	}


	@POST
	@Path("/users")
	public Response createUser(@Context HttpHeaders headers, UserDto user) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start createUser. useragent {0}", userAgent);
			return Response.ok(userService.createUser(user)).build();
		} catch (Exception e) {
			LOG.errorv("Error in createUser. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}


	@DELETE
	@Path("/users/{userId}")
	public Response deleteUser(@Context HttpHeaders headers, @PathParam("userId") String userId) {
		try {
			String userAgent = headers.getRequestHeader("user-agent").get(0);
			LOG.infov("start deleteUser. useragent {0}", userAgent);
			userService.deleteById(userId);
			LOG.info(userId);
			return Response.noContent().build();
		} catch (Exception e) {
			LOG.error("Error in deleteUser. Message {0}", e.getMessage(), e);
			return Response.serverError().build();
		}
	}
}
