package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_image")
public class UserImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private UserEntity user;

    @Column
    private String content;
}
