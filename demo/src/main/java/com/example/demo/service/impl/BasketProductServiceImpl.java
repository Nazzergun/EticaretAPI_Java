package com.example.demo.service.impl;

import com.example.demo.entity.BasketProduct;
import com.example.demo.repository.BasketProductRepository;
import com.example.demo.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {
    @Autowired
    BasketProductRepository basketProductRepository;
    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        return basketProductRepository.save(basketProduct);
    }

    @Override
    public BasketProduct findByBasketIdAndProductId(Long basketId, Long productId) {
        return basketProductRepository.findBasketProductByBasket_IdAndProduct_Id(basketId,productId);
    }
}
