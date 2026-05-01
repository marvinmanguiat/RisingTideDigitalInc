package com.YoureInGoodHandsWith.Metrobank.DTO;

import lombok.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for customer account creation requests.
 * Contains customer personal information and optional savings account details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

    /**
     * Full name of the customer.
     * Must not be blank.
     */
    @NotBlank(message = "Customer name is required")
    private String customerName;

    /**
     * Philippine mobile number in format: 09XXXXXXXXX
     * Must follow the specified regex pattern.
     */
    @Pattern(regexp = "^09\\d{9}$", message = "Invalid mobile format. Expected format: 09XXXXXXXXX")
    private String customerMobile;

    /**
     * Customer email address.
     * Must be a valid email format and not blank.
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer email is required")
    private String customerEmail;

    /**
     * Primary address line.
     * Must not be blank.
     */
    @NotBlank(message = "Address line 1 is required")
    private String address1;

    /**
     * Secondary/optional address line.
     */
    private String address2;

    /**
     * List of savings accounts to be created for this customer.
     * Each account must have valid details.
     */
    @Valid
    @Builder.Default
    private List<SavingsDTO> savings = new ArrayList<>();
}