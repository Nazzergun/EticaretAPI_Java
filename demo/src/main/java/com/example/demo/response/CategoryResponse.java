package com.example.demo.response;

import com.example.demo.dto.ProductDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
