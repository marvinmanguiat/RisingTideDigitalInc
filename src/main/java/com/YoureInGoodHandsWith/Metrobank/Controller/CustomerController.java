package com.YoureInGoodHandsWith.Metrobank.Controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.YoureInGoodHandsWith.Metrobank.DTO.CustomerRequestDTO;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Service.CustomerService;
import com.YoureInGoodHandsWith.Metrobank.Util.CustomerResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST API Controller for customer account operations.
 * Handles customer registration and retrieval endpoints.
 */
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

	private final CustomerService service;

	/**
	 * Creates a new customer account with optional savings accounts.
	 * 
	 * @param request the customer registration request containing customer details
	 *                and optional savings account information
	 * @return ResponseEntity with customer number and creation confirmation
	 */
	@PostMapping("/customer")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CustomerRequestDTO request) {
		log.info("Creating new customer with email: {}", request.getCustomerEmail());

		Customer customer = service.createCustomer(request);

		log.info("Customer created successfully with ID: {}", customer.getCustomerNumber());

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(CustomerResponseBuilder.build201Created(customer, "Customer Account created"));
	}

	/**
	 * Retrieves customer account details by customer number.
	 * 
	 * @param customerNumber the unique customer identifier
	 * @return ResponseEntity with complete customer profile and linked accounts
	 */
	@GetMapping("/account/{customerNumber}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable Long customerNumber) {
		log.info("Fetching customer account for customer number: {}", customerNumber);

		Customer customer = service.findCustomerByCustomerNum(customerNumber);

		log.info("Customer account retrieved successfully");

		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(CustomerResponseBuilder.build302Found(customer, "Customer account found"));
	}
  	
	@PutMapping("/customer/{customerNumber}")
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody CustomerRequestDTO request,
			@PathVariable Long customerNumber) {
		log.info("Updating customer with email: {}", request.getCustomerEmail());

		Customer customer = service.findCustomerByCustomerNum(customerNumber);

		Customer updatedCustomer = service.updateCustomer(customerNumber, request);

		log.info("Customer updated successfully with Customer Number : {}", customer.getCustomerNumber());

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(CustomerResponseBuilder.build201Created(updatedCustomer, "Customer Account updated"));
	}
}