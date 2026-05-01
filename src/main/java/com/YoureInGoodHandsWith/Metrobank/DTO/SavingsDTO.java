package com.YoureInGoodHandsWith.Metrobank.DTO;

import com.YoureInGoodHandsWith.Metrobank.Entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsDTO {

    @NotBlank
    private String accountNumber;

    @NotNull
    @Positive
    private Double balance;

    @NotNull
    private AccountType accountType;
}