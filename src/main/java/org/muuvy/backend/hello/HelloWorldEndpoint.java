package org.muuvy.backend.hello;

import org.jboss.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;


@Path("/hello")
public class HelloWorldEndpoint {
	private static final Logger LOG = Logger.getLogger(HelloWorldEndpoint.class);


	@GET
	@Produces("text/plain")
	public Response doGet() {
		LOG.info("Hello World");
		LOG.warn("WaRnInG");
		LOG.error("HELLO ERROR WORLD");
		LOG.fatal("World is fatal");
		LOG.trace("TRACE WoRlD");
		LOG.debug("DeBuG WoRlD");
		return Response.ok("Hello from Thorntail!").build();
	}

}
