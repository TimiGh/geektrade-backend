package com.geektrade.geektradebackend.dto;

import lombok.Data;

@Data
public class UserProfileUpdateDto {
    private String name;
    private String email;
    private String phone;
    private String county;
    private String city;
    private String street;
}
