package com.YoureInGoodHandsWith.Metrobank.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}