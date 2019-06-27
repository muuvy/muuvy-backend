package org.muuvy.backend;

import org.wildfly.swarm.Swarm;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class RestServiceTest {
    public static void main(String[] inArgs) throws Exception {
        Swarm.main(inArgs);
    }
}
