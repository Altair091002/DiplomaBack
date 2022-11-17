package com.example.demoauth.jwt;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	@Value("${app.jwtExpirationMin}")
	private int jwtExpirationMin;

	@Value("${app.jwt_secret}")
	private String secret;

	public String generateToken(String username, List<String> roles){
		Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMin).toInstant());
		return JWT.create().withSubject("user details")
				.withClaim("username", username).withClaim("roles", roles)
				.withIssuedAt(new Date()).withIssuer("altair").withExpiresAt(expirationDate)
				.sign(Algorithm.HMAC256(secret)) ;
	}

	public Map<String, Claim> validateTokenAndRetrieveClaims(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
				.withSubject("user details")
				.withIssuer("altair")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaims();
	}

}
