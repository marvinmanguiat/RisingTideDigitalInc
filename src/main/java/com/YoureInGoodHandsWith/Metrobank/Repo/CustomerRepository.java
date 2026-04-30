package com.YoureInGoodHandsWith.Metrobank.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Optional<Customer> findByCustomerNumber(Long customerNumber);
	Optional<Customer> findByCustomerEmail(String email);
}
