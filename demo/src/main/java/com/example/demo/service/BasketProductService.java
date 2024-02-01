package com.example.demo.service;

import com.example.demo.entity.BasketProduct;

public interface BasketProductService {

    BasketProduct save(BasketProduct basketProduct);

    BasketProduct findByBasketIdAndProductId(Long basketId,Long productId);

}
