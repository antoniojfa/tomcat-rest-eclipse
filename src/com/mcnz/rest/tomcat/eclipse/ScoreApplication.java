package com.mcnz.rest.tomcat.eclipse;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/")
public class ScoreApplication extends ResourceConfig {

	public ScoreApplication() {
        packages("com.mcnz.rest.tomcat.eclipse");
        
    }
}
