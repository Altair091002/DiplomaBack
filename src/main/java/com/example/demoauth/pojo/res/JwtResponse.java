package com.example.demoauth.pojo.res;

import com.example.demoauth.models.Person;
import lombok.Data;


@Data
public class JwtResponse {
	private String token;

	public JwtResponse(String token) {
		this.token = token;
	}
}
