package com.sipl.vehicle.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(VehicleIdNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleCustomerException(VehicleIdNotFoundException ex, WebRequest request) {
		ApiErrorResponse em = new ApiErrorResponse();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
	}


}
