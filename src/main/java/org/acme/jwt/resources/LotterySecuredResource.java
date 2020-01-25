package org.acme.jwt.resources;

import org.acme.jwt.service.LotteryService;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

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
	@RolesAllowed({"Echoer", "Subscriber"})
	@Produces(MediaType.TEXT_PLAIN)
	public String winners() {
		if (username.isPresent()) {
			System.out.println("username=" + username.get());
		}

		return lotteryService.winners();
	}
}