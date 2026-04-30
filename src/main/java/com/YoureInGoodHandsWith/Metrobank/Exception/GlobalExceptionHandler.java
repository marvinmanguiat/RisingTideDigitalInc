package com.YoureInGoodHandsWith.Metrobank.Exception;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.YoureInGoodHandsWith.Metrobank.Util.ErrorResponse;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 BUSINESS EXCEPTION (your existing one)
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerEX(CustomerException ex) {

        List<String> errors = ex.getErrors();

        String message;

        if (errors != null && !errors.isEmpty()) {
            message = String.join(", ", errors);
        } else {
            message = ex.getMessage();
        }

        ErrorResponse response = ErrorResponse.builder()
                .transactionStatusCode(400)
                .transactionStatusDescription(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationEX(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });

        String message = String.join(", ", errors);

        ErrorResponse response = ErrorResponse.builder()
                .transactionStatusCode(400)
                .transactionStatusDescription(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}