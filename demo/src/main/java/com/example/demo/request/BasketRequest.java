package com.example.demo.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketRequest {
    private Long userId;
    private Long productId;
    private int count;

}
