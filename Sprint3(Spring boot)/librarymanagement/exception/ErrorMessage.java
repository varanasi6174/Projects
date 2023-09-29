package com.librarymanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {	
	public ErrorMessage(HttpStatus notFound, String message2) {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unused")
	private HttpStatus status;
	@SuppressWarnings("unused")
	private String message ;	
	
}
