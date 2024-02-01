package com.example.demo.repository;

import com.example.demo.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {
     BasketProduct findBasketProductByBasket_IdAndProduct_Id(Long basketId, Long productId);
}
