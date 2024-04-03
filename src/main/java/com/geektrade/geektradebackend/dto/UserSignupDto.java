package com.geektrade.geektradebackend.dto;

import lombok.Data;

@Data
public class UserSignupDto {

    private String email;

    private String username;

    private String password;
}
