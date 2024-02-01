package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;
    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public Product findById(Long id) {
        Product product =productRepository.findById(id).get();
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryService.findById(productDto.getCategoryId()));
        product.setPrice(productDto.getPrice());
        return product;
    }
}
