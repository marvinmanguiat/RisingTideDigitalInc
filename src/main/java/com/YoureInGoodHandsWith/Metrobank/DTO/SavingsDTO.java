package com.YoureInGoodHandsWith.Metrobank.DTO;

import com.YoureInGoodHandsWith.Metrobank.Entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * DTO for savings account creation requests.
 * Contains account details including account number, balance, and type.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsDTO {

    /**
     * Unique account number for the savings account.
     * Must not be blank and must be unique across all accounts.
     */
    @NotBlank(message = "Account number is required")
    private String accountNumber;

    /**
     * Initial account balance.
     * Must be a positive value.
     */
    @NotNull(message = "Balance is required")
    @Positive(message = "Balance must be a positive value")
    private Double balance;

    /**
     * Type of savings account (e.g., SAVINGS, CHECKING).
     * Must not be null.
     */
    @NotNull(message = "Account type is required")
    private AccountType accountType;
}