package com.geektrade.geektradebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserImage {
    private Long id;
    private Long userId;
    private String content;

}
