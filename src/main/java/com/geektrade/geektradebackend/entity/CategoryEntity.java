package com.geektrade.geektradebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

}
