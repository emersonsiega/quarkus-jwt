package org.acme.jwt.resources;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.jwt.service.LotteryService;
import org.acme.jwt.utils.TokenUtils;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@Path("/secured")
@RequestScoped
public class LotterySecuredResource {

	@Inject
	LotteryService lotteryService;

	@Inject
	@Claim(standard = Claims.preferred_username)
	Optional<JsonString> username;

	@GET
	@Path("winners")
	@RolesAllowed({ TokenUtils.ROLE_USER })
	@Produces(MediaType.TEXT_PLAIN)
	public String winners() {
		if (username.isPresent()) {
			System.out.println("username=" + username.get());
		}

		return lotteryService.winners();
	}

	@GET
	@Path("verify")
	@RolesAllowed({ TokenUtils.ROLE_ADMIN })
	@Produces(MediaType.TEXT_PLAIN)
	public String verify() {
		return "Some administration service...";
	}
}