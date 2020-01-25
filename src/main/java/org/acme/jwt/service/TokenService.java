package org.acme.jwt.service;

import org.acme.jwt.utils.TokenUtils;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;

import javax.enterprise.context.RequestScoped;
import java.util.Arrays;

@RequestScoped
public class TokenService {

	public String generate(String email, String username, String birthdate) {
		try {
			System.out.println("creating account");

			JwtClaims jwtClaims = new JwtClaims();
			jwtClaims.setIssuer("https://quarkus.io/using-jwt-rbac");
			jwtClaims.setJwtId("a-123");
			jwtClaims.setSubject(email);
			jwtClaims.setClaim(Claims.upn.name(), email);
			jwtClaims.setClaim(Claims.preferred_username.name(), username);
			jwtClaims.setClaim(Claims.birthdate.name(), birthdate);
			jwtClaims.setClaim(Claims.groups.name(), Arrays.asList(TokenUtils.ROLE_USER));
			jwtClaims.setAudience("using-jwt");
			jwtClaims.setExpirationTimeMinutesInTheFuture(1);

			String token = TokenUtils.generateTokenString(jwtClaims);

			System.out.println(token);

			return token;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Oops!");
		}

	}
}