package com.YoureInGoodHandsWith.Metrobank.Repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;

import com.YoureInGoodHandsWith.Metrobank.Entity.AccountType;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;
import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Test
	public void CustomerRepository_SaveCustomer_ReturnCustomer(){
		Customer customer = Customer.builder()
				.customerName("donald trump")
				.customerMobile("09171342333")
				.customerEmail("djt2026@testemail010101.com")
				.address1("address line 1 ")
				.address2("address line 2 " )
				.build();
		
		List<SavingsAccount> savingsList = new ArrayList<>();
		SavingsAccount sa = SavingsAccount.builder()
				       .accountNumber("SA00000000001")
				       .accountType(AccountType.S)
				       .balance(699999999998888888.00)
				       .customer(customer)
				       .build();
		
		
		Customer newCustomer  = customerRepository.save(customer);
		
		assertThat(newCustomer).isNotNull();
		assertThat(newCustomer.getSavings().size()>1);
		
	}
	
}