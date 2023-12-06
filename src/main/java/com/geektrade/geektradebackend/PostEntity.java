package com.geektrade.geektradebackend;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private UserEntity user;
    private String title;
}
