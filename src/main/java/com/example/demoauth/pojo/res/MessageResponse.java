package com.example.demoauth.pojo.res;

import lombok.Data;

@Data
public class MessageResponse {
	
	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}
		
}
