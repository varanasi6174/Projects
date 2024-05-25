package com.jpa.hibernate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	    info = @Info(
	        title = "User API",
	        description = "Doing CRUD Operations",
	        summary = "User API will handle GET, POST, DELETE, and PUT operations",
	        termsOfService = "T&C",
	        version = "v1"
	    ),
	    
	    servers = {
	    		@Server(
	    				description = "Dev",
	    				url = "http://localhost:8080"
	    				),
	     		@Server(
	    				description = "Test",
	    				url = "http://localhost:8080"
	    				)
	    }
	)
@SecurityScheme(
	    name = "auth",
	    in = SecuritySchemeIn.HEADER,
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer",
	    description = "security desc"
	)

public class SwaggerConfig {

	
}
