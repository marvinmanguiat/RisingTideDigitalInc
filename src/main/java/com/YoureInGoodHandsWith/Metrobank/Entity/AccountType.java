package com.YoureInGoodHandsWith.Metrobank.Entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    S("SAVINGS"),
    C("CHECKING");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

}