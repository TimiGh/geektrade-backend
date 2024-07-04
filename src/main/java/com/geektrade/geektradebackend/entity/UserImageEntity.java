package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_image")
public class UserImageEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserEntity user;

    @Column(columnDefinition = "text")
    private String content;
}
