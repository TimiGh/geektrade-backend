package com.geektrade.geektradebackend.entity;

import com.geektrade.geektradebackend.dto.Listing;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "listing")
public class ListingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String quality;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column
    private Integer price;

    @Column(name = "is_negotiable", nullable = false)
    private Boolean isNegotiable;

    @Column(name = "seen_counter", nullable = false)
    private Integer seenCounter;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Listing toPojo() {
        Listing listing = new Listing();
        listing.setId(id);
        listing.setTitle(title);
        listing.setPrice(price);
        listing.setIsNegotiable(isNegotiable);
        listing.setCategoryId(categoryId);
        listing.setQuality(quality);
        listing.setDescription(description);
        listing.setUser(user.toPojo());

        return listing;
    }
}
