package org.acme.jwt.resources;

import org.acme.jwt.service.TokenService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Emerson Siega <emerson.siega@kepha.com.br>
 */
@Path("/register")
@RequestScoped
public class LoginResource {

	@Inject
	TokenService tokenService;

	@GET
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	public String create(@QueryParam("email") String email,
						 @QueryParam("username") String username,
						 @QueryParam("birthdate") String birthdate) {

		String token = tokenService.generate(email, username, birthdate);

		return token;
	}

}
