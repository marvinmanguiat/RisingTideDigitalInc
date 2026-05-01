package com.YoureInGoodHandsWith.Metrobank.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.YoureInGoodHandsWith.Metrobank.DTO.CustomerRequestDTO;
import com.YoureInGoodHandsWith.Metrobank.DTO.SavingsDTO;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;
import com.YoureInGoodHandsWith.Metrobank.Exception.CustomerException;
import com.YoureInGoodHandsWith.Metrobank.Repo.CustomerRepository;
import com.YoureInGoodHandsWith.Metrobank.Repo.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service layer for customer account management.
 * Handles business logic for customer creation, validation, and account retrieval.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final SavingsAccountRepository savingsRepo;

	/**
	 * Creates a new customer profile with associated savings accounts.
	 * Validates customer information and account numbers before creation.
	 * 
	 * @param customerDTO the customer request containing customer and account details
	 * @return the created Customer entity with assigned customer number
	 * @throws CustomerException if validation fails (duplicate email or account number)
	 */
	@Transactional
	public Customer createCustomer(CustomerRequestDTO customerDTO) {
		log.debug("Starting customer creation for email: {}", customerDTO.getCustomerEmail());
		
		List<String> errors = new ArrayList<>();
		validateCustomerData(customerDTO, errors);
		validateSavingsAccounts(customerDTO, errors);

		if (!errors.isEmpty()) {
			log.warn("Customer creation validation failed with {} error(s)", errors.size());
			throw new CustomerException(errors);
		}

		Customer customer = buildCustomer(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		
		log.info("Customer created successfully with ID: {}", savedCustomer.getCustomerNumber());
		return savedCustomer;
	}

	/**
	 * Retrieves a customer by their unique identifier.
	 * 
	 * @param id the customer ID
	 * @return the Customer entity
	 * @throws CustomerException if customer not found
	 */
	public Customer getById(Long id) {
		log.debug("Retrieving customer by ID: {}", id);
		return customerRepository.findById(id)
				.orElseThrow(() -> {
					log.warn("Customer not found with ID: {}", id);
					return new CustomerException("Customer not found");
				});
	}

	/**
	 * Retrieves a customer by their customer number.
	 * 
	 * @param customerNumber the customer number
	 * @return the Customer entity with associated savings accounts
	 * @throws CustomerException if customer not found
	 */
	public Customer findCustomerByCustomerNum(Long customerNumber) {
		log.debug("Retrieving customer by customer number: {}", customerNumber);
		return customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> {
					log.warn("Customer not found with customer number: {}", customerNumber);
					return new CustomerException("Customer not found");
				});
	}

	/**
	 * Validates customer email and required fields.
	 * 
	 * @param customerDTO the customer request
	 * @param errors list to collect validation errors
	 */
	private void validateCustomerData(CustomerRequestDTO customerDTO, List<String> errors) {
		String email = customerDTO.getCustomerEmail();

		if (email == null || email.trim().isEmpty()) {
			errors.add("Customer email is required");
			return;
		}

		if (customerRepository.findByCustomerEmail(email).isPresent()) {
			errors.add("Customer with that email address already exists");
		}
	}

	/**
	 * Validates savings account data and uniqueness of account numbers.
	 * 
	 * @param customerDTO the customer request
	 * @param errors list to collect validation errors
	 */
	private void validateSavingsAccounts(CustomerRequestDTO customerDTO, List<String> errors) {
		List<SavingsDTO> savings = customerDTO.getSavings();
		
		if (savings == null || savings.isEmpty()) {
			return;
		}

		for (SavingsDTO savingsDTO : savings) {
			if (savingsRepo.existsByAccountNumber(savingsDTO.getAccountNumber())) {
				errors.add("Savings account number " + savingsDTO.getAccountNumber() + " already exists");
				break;
			}
		}
	}

	/**
	 * Builds a Customer entity from the request DTO with associated savings accounts.
	 * 
	 * @param customerDTO the customer request
	 * @return Customer entity ready to be persisted
	 */
	private Customer buildCustomer(CustomerRequestDTO customerDTO) {
		Customer customer = Customer.builder()
				.customerName(customerDTO.getCustomerName())
				.customerMobile(customerDTO.getCustomerMobile())
				.customerEmail(customerDTO.getCustomerEmail())
				.address1(customerDTO.getAddress1())
				.address2(customerDTO.getAddress2())
				.build();

		List<SavingsDTO> savingsDtos = customerDTO.getSavings();
		if (savingsDtos != null && !savingsDtos.isEmpty()) {
			savingsDtos.forEach(savingsDTO -> {
				SavingsAccount account = SavingsAccount.builder()
						.accountNumber(savingsDTO.getAccountNumber())
						.balance(savingsDTO.getBalance())
						.accountType(savingsDTO.getAccountType())
						.customer(customer)
						.build();
				customer.getSavings().add(account);
			});
		}

		return customer;
	}
}