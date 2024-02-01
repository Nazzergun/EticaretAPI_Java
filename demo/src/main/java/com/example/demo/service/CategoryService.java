package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    Category findById(Long id);
}
