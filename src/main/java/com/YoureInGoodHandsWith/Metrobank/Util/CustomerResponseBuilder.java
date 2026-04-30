package com.YoureInGoodHandsWith.Metrobank.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;

public class CustomerResponseBuilder {

	
    public static Map<String, Object> build302Found(Customer customer, String description) {

        Map<String, Object> response = new LinkedHashMap<>();

    //    response.put("id", customer.getId());
        response.put("customerNumber", customer.getCustomerNumber());
        response.put("customerName", customer.getCustomerName());
        response.put("customerMobile", customer.getCustomerMobile());
        response.put("customerEmail", customer.getCustomerEmail());
        response.put("address1", customer.getAddress1());
        response.put("address2", customer.getAddress2());
        response.put("savings", customer.getSavings());

        response.put("transactionStatusCode", 302);
        response.put("transactionStatusDescription", description);

        return response;
    }
    
    
    public static Map<String, Object> build201Created(Customer customer, String description) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("customerNumber", customer.getCustomerNumber());
        response.put("transactionStatusCode", 201);
        response.put("transactionStatusDescription", description);

        return response;
    }
}
