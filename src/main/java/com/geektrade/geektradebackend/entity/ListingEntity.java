package com.geektrade.geektradebackend.entity;

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

//    @Column(name = "user_id", nullable = false)
//    private Long userId;

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
}
