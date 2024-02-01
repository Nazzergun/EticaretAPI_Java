package com.example.demo.response;

import com.example.demo.dto.BasketProductDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse {
    private Long id;
    private int status;
    private double totalPrice;
    private Long userId;
    private List<BasketProductDto> basketProducts;
}
