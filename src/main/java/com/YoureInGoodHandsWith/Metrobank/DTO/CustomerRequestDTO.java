package com.YoureInGoodHandsWith.Metrobank.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

    @NotBlank
    private String customerName;

    @Pattern(regexp = "^09\\d{9}$", message = "Invalid mobile format")
    private String customerMobile;

    @Email
    @NotBlank
    private String customerEmail;

    @NotBlank
    private String address1;

    private String address2;

    @Valid
    private List<SavingsDTO> savings = new ArrayList<>();
}