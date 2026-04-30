package com.YoureInGoodHandsWith.Metrobank.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class SavingsAccountRepositoryTest {
	
	@Autowired
	SavingsAccountRepository savingsAccountRepository;
	
	
	

}
