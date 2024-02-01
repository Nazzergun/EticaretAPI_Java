package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.request.ProductRequest;
import com.example.demo.response.ProductResponse;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return toResponse(productService.save(toDto(productRequest)));
    }

    public ProductResponse toResponse(ProductDto product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategoryId(product.getCategoryId());
        productResponse.setPrice(product.getPrice());

        return productResponse;
    }

    public ProductDto toDto(ProductRequest productRequest) {
        ProductDto productDto = new ProductDto();
        productDto.setName(productRequest.getName());
        productDto.setDescription(productRequest.getDescription());
        productDto.setCategoryId(productRequest.getCategoryId());
        productDto.setPrice(productRequest.getPrice());

        return productDto;
    }
}
