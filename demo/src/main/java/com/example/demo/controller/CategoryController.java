package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.request.CategoryRequest;
import com.example.demo.response.CategoryResponse;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return toResponse(categoryService.save(toDto(categoryRequest)));
    }

    public CategoryResponse toResponse(CategoryDto category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setProducts(category.getProducts());
        return categoryResponse;
    }

    public CategoryDto toDto(CategoryRequest categoryRequest) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryRequest.getName());
        categoryDto.setDescription(categoryRequest.getDescription());
        return categoryDto;
    }
}
