package com.YoureInGoodHandsWith.Metrobank.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.YoureInGoodHandsWith.Metrobank.DTO.CustomerRequestDTO;
import com.YoureInGoodHandsWith.Metrobank.DTO.SavingsDTO;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;
import com.YoureInGoodHandsWith.Metrobank.Exception.CustomerException;
import com.YoureInGoodHandsWith.Metrobank.Repo.CustomerRepository;
import com.YoureInGoodHandsWith.Metrobank.Repo.SavingsAccountRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final SavingsAccountRepository savingsRepo;

	@Autowired
	CustomerService(CustomerRepository customerRepository, SavingsAccountRepository savingsRepo) {
		this.customerRepository = customerRepository;
		this.savingsRepo = savingsRepo;
	}
	
	
/**
 * Create customer profile along w/ savings account or checking account or both can be created at once.
 * @param customerDTO
 * @return Customer
 */
	public Customer createCustomer(CustomerRequestDTO customerDTO) {
		List<String> errors = new ArrayList<>();
	
		String email = customerDTO.getCustomerEmail();

		if (customerRepository.findByCustomerEmail(email).isPresent()) {
			errors.add("Customer with that email address already exist");
		}
		
		if (email == null || email.trim().isEmpty()) {
		    errors.add("Customer email is required");
		}

		if(customerDTO.getSavings()!=null)
		for (SavingsDTO savings : customerDTO.getSavings()) {
			if (savingsRepo.existsByAccountNumber(savings.getAccountNumber())) {
				errors.add("Savins Account number exists");
				break; 
			}
		}

		if (!errors.isEmpty()) {
			throw new CustomerException(errors);
		}

		Customer customer = Customer.builder()
				.customerName(customerDTO.getCustomerName())
				.customerMobile(customerDTO.getCustomerMobile())
				.customerEmail(customerDTO.getCustomerEmail())
				.address1(customerDTO.getAddress1())
				.address2(customerDTO.getAddress2())
				.build();

		List<SavingsAccount> savingsList = new ArrayList<>();
   
		if(customerDTO.getSavings()!=null)
		for (SavingsDTO s : customerDTO.getSavings()) {

			SavingsAccount account = SavingsAccount.builder()
					.accountNumber(s.getAccountNumber())
					.balance(s.getBalance())
					.accountType(s.getAccountType())
					.customer(customer).build();

			savingsList.add(account);
			customer.getSavings().add(account);
		}

		return customerRepository.save(customer);
	}

	public Customer getById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer not found"));
	}

	public Customer findCustomerByCustomerNum(Long customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new CustomerException("Customer not found"));
	}
}