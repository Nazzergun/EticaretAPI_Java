package com.example.demo.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductRequest {
    private String name;
    private String description;
    private Long categoryId;
    private Double price;
}
