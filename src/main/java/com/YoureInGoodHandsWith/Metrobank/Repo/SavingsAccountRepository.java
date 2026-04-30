package com.YoureInGoodHandsWith.Metrobank.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YoureInGoodHandsWith.Metrobank.Entity.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
	Optional<SavingsAccount> findByAccountNumber(String accountNumber);

	boolean existsByAccountNumber(String accountNumber);

}
