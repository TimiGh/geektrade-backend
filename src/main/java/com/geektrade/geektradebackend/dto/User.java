package com.geektrade.geektradebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String role;
    private String street;
    private String county;
    private String city;
    private String phone;
    private Long profileImageKey;
    private LocalDateTime createdAt;

}
