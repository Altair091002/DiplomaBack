package com.example.demoauth.pojo.res;

import lombok.Data;


@Data
public class JwtResponse {
	private String token;
	private String username;
	private String email;

	public JwtResponse(String token, String username, String email) {
		this.token = token;
		this.username = username;
		this.email = email;
	}
}
