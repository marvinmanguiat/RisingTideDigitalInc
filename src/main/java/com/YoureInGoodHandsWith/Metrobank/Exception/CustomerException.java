package com.YoureInGoodHandsWith.Metrobank.Exception;

import java.util.List;

/**
 * Custom exception for customer-related business logic errors.
 * Can be used for single error messages or multiple validation errors.
 */
public class CustomerException extends RuntimeException {

	private final List<String> errors;

	/**
	 * Creates a customer exception with a single error message.
	 * 
	 * @param message the error message
	 */
	public CustomerException(String message) {
		super(message);
		this.errors = null;
	}

	/**
	 * Creates a customer exception with multiple validation errors.
	 * 
	 * @param errors list of error messages from validation
	 */
	public CustomerException(List<String> errors) {
		super("Validation failed");
		this.errors = errors;
	}

	/**
	 * Gets the list of validation errors if applicable.
	 * 
	 * @return list of error messages or null if single message exception
	 */
	public List<String> getErrors() {
		return errors;
	}
}
