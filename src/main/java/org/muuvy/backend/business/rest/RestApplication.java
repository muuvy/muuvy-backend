package org.muuvy.backend.business.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

	private static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("muuvy");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
