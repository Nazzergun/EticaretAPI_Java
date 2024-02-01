package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = toEntity(categoryDto);
        category = categoryRepository.save(category);
        return toDto(category);
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        category.setProducts(category.getProducts());
        return categoryDto;

//        return CategoryDto.builder()
//                .id(category.getId())
//                .description(category.getDescription())
//                .name(category.getName())
//                .build();
    }

    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
