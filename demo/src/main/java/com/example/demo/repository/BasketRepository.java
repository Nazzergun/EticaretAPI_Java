package com.example.demo.repository;

import com.example.demo.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket , Long> {
    Basket findByUser_IdAndStatusEquals(Long userId, int status);
}
