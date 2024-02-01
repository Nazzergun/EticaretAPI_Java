package com.example.demo.dto;

import com.example.demo.entity.BasketProduct;
import com.example.demo.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    private int status;
    private double totalPrice;
    private Long userId;
    private List<BasketProductDto> basketProducts;
    private int count;
    private Long productId;

}
