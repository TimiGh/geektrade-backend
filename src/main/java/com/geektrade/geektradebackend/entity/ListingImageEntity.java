package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "listing_image")
public class ListingImageEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long listingId;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @Column(columnDefinition = "text")
    private String content;
}
