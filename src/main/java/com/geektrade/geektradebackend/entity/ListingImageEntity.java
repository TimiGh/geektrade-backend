package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "listing_image")
public class ListingImageEntity {
    @Id
    @GeneratedValue
    private Long id;


    private String listingId;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @Column
    private String content;
}
