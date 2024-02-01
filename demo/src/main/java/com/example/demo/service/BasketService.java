package com.example.demo.service;

import com.example.demo.dto.BasketDto;
import com.example.demo.request.BasketRequest;

public interface BasketService {
    BasketDto save(BasketDto basketDto);

    void delete(Long id);
}
