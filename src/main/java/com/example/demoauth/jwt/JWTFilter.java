package com.example.demoauth.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.example.demoauth.service.PersonDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	private final PersonDetailsServiceImpl personDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
			String jwt = authHeader.substring(7);
			if (!jwt.isBlank()) {
				try {
					Map<String, Claim> jwtClaims = jwtUtil.validateTokenAndRetrieveClaims(jwt);
					if (SecurityContextHolder.getContext().getAuthentication() == null){
						UserDetails userDetails = personDetailService.loadUserByUsername(jwtClaims.get("username").asString());
						UsernamePasswordAuthenticationToken authToken =
								new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUsername(), userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JWTVerificationException exception) {
					System.out.println("filter exception " + exception);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
