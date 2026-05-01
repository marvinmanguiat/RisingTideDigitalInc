package com.YoureInGoodHandsWith.Metrobank.Entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of supported account types.
 * Defines the types of accounts a customer can create.
 */
@Getter
@AllArgsConstructor
public enum AccountType {

    /**
     * Savings account for long-term deposits with interest.
     */
    S("SAVINGS"),

    /**
     * Checking account for frequent transactions.
     */
    C("CHECKING");

    /**
     * Human-readable description of the account type.
     */
    private final String description;

    /**
     * Gets the JSON representation of the account type.
     * 
     * @return the description string
     */
    @JsonValue
    public String getDescription() {
        return description;
    }
}