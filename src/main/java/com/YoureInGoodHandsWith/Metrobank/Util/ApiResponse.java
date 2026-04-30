package com.YoureInGoodHandsWith.Metrobank.Util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
	private T response;
    private int transactionStatusCode;
    private String transactionStatusDescription;
}