package com.YoureInGoodHandsWith.Metrobank.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;

/**
 * Spring Data JPA repository for SavingsAccount entity persistence.
 * Provides CRUD operations and custom query methods for savings account data access.
 */
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    
    /**
     * Finds a savings account by its unique account number.
     * 
     * @param accountNumber the account's unique identifier
     * @return Optional containing the savings account if found, empty otherwise
     */
    Optional<SavingsAccount> findByAccountNumber(String accountNumber);

    /**
     * Checks if a savings account with the given account number exists.
     * Useful for validating account number uniqueness before creation.
     * 
     * @param accountNumber the account number to check
     * @return true if an account with this number exists, false otherwise
     */
    boolean existsByAccountNumber(String accountNumber);
}
