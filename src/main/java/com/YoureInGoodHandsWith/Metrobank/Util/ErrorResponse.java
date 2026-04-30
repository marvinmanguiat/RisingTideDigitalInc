package com.YoureInGoodHandsWith.Metrobank.Util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int transactionStatusCode;
    private String transactionStatusDescription;
}