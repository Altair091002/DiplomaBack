package com.example.demoauth.dto.res;

import com.example.demoauth.jwt.PersonDetailsImpl;
import lombok.Data;


@Data
public class JwtResponse {
	private String token;
	private String username;
	private String email;

	private PersonDetailsImpl personDetails;

	public JwtResponse(String token, String username, String email) {
		this.token = token;
		this.username = username;
		this.email = email;
	}

	public JwtResponse(String token, PersonDetailsImpl personDetails) {
		this.token = token;
		this.personDetails = personDetails;
	}

	public JwtResponse(String token) {
		this.token = token;
	}
}
