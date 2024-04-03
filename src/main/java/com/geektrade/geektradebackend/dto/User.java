package com.geektrade.geektradebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

private String username;
private String email;
private String street;
private String county;
private String city;
private String phone;
private String profileImageKey;
private LocalDateTime createdAt;

}
