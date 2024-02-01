package com.example.demo.controller;

import com.example.demo.dto.BasketDto;
import com.example.demo.dto.BasketProductDto;
import com.example.demo.request.BasketRequest;
import com.example.demo.response.BasketResponse;
import com.example.demo.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController {
    @Autowired
    BasketService basketService;
    @PostMapping
    public BasketResponse save(BasketRequest request){
        return toResponse(basketService.save(toDto(request)));
    }

    private BasketResponse toResponse(BasketDto basketDto){
        BasketResponse basketResponse=new BasketResponse();
        basketResponse.setId(basketDto.getId());
        basketResponse.setStatus(basketDto.getStatus());
        basketResponse.setTotalPrice(basketDto.getTotalPrice());
        basketResponse.setUserId((basketDto.getUserId()));
        basketResponse.setBasketProducts(basketDto.getBasketProducts());
         return  basketResponse;
    }
    private  BasketDto toDto(BasketRequest request){
        List<BasketProductDto> basketProductDtoList = new ArrayList<>();
        basketProductDtoList.add(new BasketProductDto(request.getProductId(),request.getCount()));

        BasketDto basketDto=new BasketDto();
        basketDto.setUserId(request.getUserId());
        basketDto.setBasketProducts(basketProductDtoList);
        basketDto.setCount(request.getCount());
        return  basketDto;
    }
}
