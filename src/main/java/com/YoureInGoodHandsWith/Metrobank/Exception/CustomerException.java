package com.YoureInGoodHandsWith.Metrobank.Exception;

import java.util.List;

public class CustomerException extends RuntimeException {

	private final List<String> errors;

	public CustomerException(String message) {
		super(message);
		this.errors = null;
		
	}

	public CustomerException(List<String> errors) {
		super("Validation failed");
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}
}
