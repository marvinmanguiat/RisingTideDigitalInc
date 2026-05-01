package com.YoureInGoodHandsWith.Metrobank.Util;

import java.util.LinkedHashMap;
import java.util.Map;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;

/**
 * Utility class for building standardized API response objects.
 * Provides factory methods to create consistent response structures.
 */
public class CustomerResponseBuilder {

    /**
     * Builds a successful retrieval response (HTTP 200 OK).
     * 
     * @param customer the customer entity to include in response
     * @param description human-readable response description
     * @return map containing customer details and response metadata
     */
    public static Map<String, Object> build302Found(Customer customer, String description) {
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("customerNumber", customer.getCustomerNumber());
        response.put("customerName", customer.getCustomerName());
        response.put("customerMobile", customer.getCustomerMobile());
        response.put("customerEmail", customer.getCustomerEmail());
        response.put("address1", customer.getAddress1());
        response.put("address2", customer.getAddress2());
        response.put("savings", customer.getSavings());
        response.put("transactionStatusCode", 200);
        response.put("transactionStatusDescription", description);

        return response;
    }

    /**
     * Builds a successful creation response (HTTP 201 CREATED).
     * 
     * @param customer the newly created customer entity
     * @param description human-readable response description
     * @return map containing customer number and response metadata
     */
    public static Map<String, Object> build201Created(Customer customer, String description) {
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("customerNumber", customer.getCustomerNumber());
        response.put("transactionStatusCode", 201);
        response.put("transactionStatusDescription", description);

        return response;
    }
}
