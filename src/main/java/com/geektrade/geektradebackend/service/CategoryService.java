package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.dto.Category;
import com.geektrade.geektradebackend.entity.CategoryEntity;
import com.geektrade.geektradebackend.entity.ListingImageEntity;
import com.geektrade.geektradebackend.repository.CategoryRepository;
import com.geektrade.geektradebackend.repository.ListingImageRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll().stream().map(CategoryEntity::toCategory).collect(Collectors.toList());
    }
}
