package com.geektrade.geektradebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDto {
    private String term;
    private String categoryId;
    private Long priceMin;
    private Long priceMax;
    private String quality;
}
