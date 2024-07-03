package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "listing_image")
@Data
public class ListingImageEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long listingId;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @Column(nullable = false, columnDefinition = "text")
    private String content;
}
