package com.sipl.vehicle.management.exception;

public class CustomDataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomDataIntegrityException(String message) {
        super(message);
    }
}
