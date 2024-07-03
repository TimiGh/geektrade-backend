package com.geektrade.geektradebackend.controller;

import com.geektrade.geektradebackend.dto.Category;
import com.geektrade.geektradebackend.service.CategoryService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categories")
@Data
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getAll();
    }
}
