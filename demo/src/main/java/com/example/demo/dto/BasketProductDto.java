package com.example.demo.dto;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductDto {
    public int get;
    private Long id;
    private Long productId;
    private int count;
    private double totalPrice;
    private Long basketId;

    public  BasketProductDto(Long productId,int count){
        this.productId=productId;
        this.count=count;
    }
}
