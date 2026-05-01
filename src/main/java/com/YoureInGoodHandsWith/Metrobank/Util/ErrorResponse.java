package com.YoureInGoodHandsWith.Metrobank.Util;

import lombok.*;

/**
 * Standard error response format for API errors.
 * Contains HTTP status code and descriptive error message.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * HTTP status code indicating the type of error.
     */
    private int transactionStatusCode;

    /**
     * Human-readable description of the error.
     */
    private String transactionStatusDescription;
}