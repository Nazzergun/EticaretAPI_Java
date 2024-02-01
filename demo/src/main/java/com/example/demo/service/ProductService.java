package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    Product findById (Long id);

}
