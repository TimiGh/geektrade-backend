package com.geektrade.geektradebackend.entity;

import com.geektrade.geektradebackend.dto.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    private Long id;

    @Column
    private String name;

    public Category toCategory() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

}
