package com.YoureInGoodHandsWith.Metrobank.Exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.YoureInGoodHandsWith.Metrobank.Util.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for centralizing error handling across the application.
 * Provides consistent error response format for all exceptions.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles business-level exceptions for customer operations.
     * 
     * @param ex the CustomerException containing error details
     * @return ResponseEntity with error response in BAD_REQUEST status
     */
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerException(CustomerException ex) {
        List<String> errors = ex.getErrors();
        String message = (errors != null && !errors.isEmpty()) 
            ? String.join(", ", errors) 
            : ex.getMessage();

        log.warn("Customer validation error: {}", message);

        ErrorResponse response = ErrorResponse.builder()
                .transactionStatusCode(400)
                .transactionStatusDescription(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles validation errors for request body parameters.
     * 
     * @param ex the MethodArgumentNotValidException from Spring validation
     * @return ResponseEntity with validation error details in BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.add(error.getField() + ": " + error.getDefaultMessage())
        );

        String message = String.join(", ", errors);
        log.warn("Validation failed: {}", message);

        ErrorResponse response = ErrorResponse.builder()
                .transactionStatusCode(400)
                .transactionStatusDescription(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Fallback handler for unexpected exceptions.
     * 
     * @param ex the Exception
     * @return ResponseEntity with generic error message in INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);

        ErrorResponse response = ErrorResponse.builder()
                .transactionStatusCode(500)
                .transactionStatusDescription("An unexpected error occurred. Please contact support.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}