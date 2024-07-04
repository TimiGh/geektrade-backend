package com.geektrade.geektradebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Listing {
    private Long id;
    private String title;
    private Integer price;
    private Boolean isNegotiable;
    private Long categoryId;
    private String quality;
    private String description;
    private String imageUrl;
    private Long primaryImageId;
    private User user;
    private List<Long> imageIds;
    private LocalDateTime createdAt;
}
