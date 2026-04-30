package com.YoureInGoodHandsWith.Metrobank.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.YoureInGoodHandsWith.Metrobank.DTO.CustomerRequestDTO;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Service.CustomerService;
import com.YoureInGoodHandsWith.Metrobank.Util.ApiResponse;
import com.YoureInGoodHandsWith.Metrobank.Util.CustomerResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private final CustomerService service;

	@PostMapping("/customer")
	public ResponseEntity<?> create(@Valid @RequestBody CustomerRequestDTO request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(CustomerResponseBuilder.build201Created(service.createCustomer(request), "Customer Account created"));
		
	}
	

	@GetMapping("/account/{customerNumber}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable Long customerNumber) {
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(CustomerResponseBuilder.build302Found(service.findCustomerByCustomerNum(customerNumber), "Customer account found "));
	}
}