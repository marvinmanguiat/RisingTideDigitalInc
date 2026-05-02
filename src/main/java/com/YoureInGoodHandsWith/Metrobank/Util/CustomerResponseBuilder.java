package com.YoureInGoodHandsWith.Metrobank.Util;

import java.util.*;

import com.YoureInGoodHandsWith.Metrobank.DTO.SavingsDTO;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;

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
        List<SavingsDTO> savingDTOList  =  new ArrayList<>();

        customer.getSavings().forEach(saving -> {
            SavingsDTO  savingDTO  =  SavingsDTO.builder()
            .accountNumber(saving.getAccountNumber())
            .balance(saving.getBalance())
            .accountType(saving.getAccountType())
            .build();
            savingDTOList.add(savingDTO);
        });
        
        response.put("customerNumber", customer.getCustomerNumber());
        response.put("customerName", customer.getCustomerName());
        response.put("customerMobile", customer.getCustomerMobile());
        response.put("customerEmail", customer.getCustomerEmail());
        response.put("address1", customer.getAddress1());
        response.put("address2", customer.getAddress2());
        response.put("savings", savingDTOList);
        response.put("transactionStatusCode", 302);
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
