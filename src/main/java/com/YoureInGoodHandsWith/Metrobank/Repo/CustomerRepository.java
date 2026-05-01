package com.YoureInGoodHandsWith.Metrobank.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.YoureInGoodHandsWith.Metrobank.Entity.Customer;

/**
 * Spring Data JPA repository for Customer entity persistence.
 * Provides CRUD operations and custom query methods for customer data access.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    /**
     * Finds a customer by their unique customer number.
     * 
     * @param customerNumber the customer's unique identifier
     * @return Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findByCustomerNumber(Long customerNumber);

    /**
     * Finds a customer by their email address.
     * Email addresses are unique across the system.
     * 
     * @param email the customer's email address
     * @return Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findByCustomerEmail(String email);
}
