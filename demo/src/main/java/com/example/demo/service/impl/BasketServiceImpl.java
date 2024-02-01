package com.example.demo.service.impl;

import com.example.demo.dto.BasketDto;
import com.example.demo.dto.BasketProductDto;
import com.example.demo.entity.Basket;
import com.example.demo.entity.BasketProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.BasketRepository;
import com.example.demo.service.BasketProductService;
import com.example.demo.service.BasketService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    @Autowired
    UserService userService;

    @Autowired
    BasketProductService basketProductService;

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductService productService;

    private final int BASKET_STATUS_NONE = 0;
    private final int BASKET_STATUS_SALED = 1;


    @Override
    @Transactional
    public BasketDto save(BasketDto basketDto) {
        Basket basket = basketRepository.findByUser_IdAndStatusEquals(basketDto.getUserId(), BASKET_STATUS_NONE);
        if (basket != null) {
            return sepetVarYeniUrunuEkle(basket, basketDto);
        } else {
            return sepetYokUrunEkle(basketDto);
        }
    }

    private BasketDto sepetYokUrunEkle(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setUser(userService.findById(basketDto.getUserId()));
        basket.setStatus(BASKET_STATUS_NONE);

        List<BasketProduct>basketProducts=new ArrayList<>();

        for (BasketProductDto basketProductDto : basketDto.getBasketProducts()) {
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setProduct(productService.findById(basketProductDto.getProductId()));
            basketProduct.setCount(basketProductDto.getCount());
            basketProduct.setBasket(basket);
            basketProduct.setTotalPrice(totalPriceHesapla(basketProduct.getProduct().getPrice() , basketProduct.getCount()));
       basketProduct = basketProductService.save(basketProduct);
       basketProducts.add(basketProduct);
        }

        basket.setBasketProducts(basketProducts);
        basket.setTotalPrice(basketinTotalPriceHesapla(basketProducts));
        basket= basketRepository.save(basket);
        return toDto(basket);
    }

    private double basketinTotalPriceHesapla(List<BasketProduct> basketProducts) {
        double totalPrice =0;
        for (BasketProduct basketProduct: basketProducts){
            totalPrice +=basketProduct.getTotalPrice();
        }
        return totalPrice;
    }

    private  Double totalPriceHesapla(Double price, Integer count){
        double totalPrice=price * count;
        return  totalPrice;
 }

    private BasketDto sepetVarYeniUrunuEkle(Basket basket, BasketDto basketDto) {
        List<BasketProduct> basketProducts= basket.getBasketProducts();
        BasketProduct product =basketProductService.findByBasketIdAndProductId(basket.getId(),basketDto.getBasketProducts().get(0).getProductId() );
        if (product == null){
            BasketProduct basketProduct=new BasketProduct();
            for (BasketProductDto basketProductDto:basketDto.getBasketProducts()) {
                basketProduct.setProduct(productService.findById(basketProductDto.getProductId()));
                basketProduct.setCount(basketProductDto.getCount());
                basketProduct.setBasket(basket);
                basketProduct.setTotalPrice(totalPriceHesapla(productService.findById(basketDto.getBasketProducts().get(0).getProductId()).getPrice(), basketProduct.getCount()));
            basketProduct= basketProductService.save(basketProduct);
            basketProducts.add(basketProduct);
            }

        }else{
            for (BasketProductDto basketProductDto : basketDto.getBasketProducts()){
                product.setCount(product.getCount() * basketProductDto.getCount());
                product.setTotalPrice(totalPriceHesapla(product.getProduct().getPrice(), product.getCount()));
                basketProductService.save(product);
            }
        }
        basket.setTotalPrice(basketinTotalPriceHesapla(basketProducts));
        basket.setBasketProducts(basketProducts);
        return toDto(basket);
    }

    @Override
    public void delete(Long id) {

    }

    private BasketDto toDto(Basket basket){
        List<BasketProductDto>basketProductDtos=new ArrayList<>();
        for (BasketProduct basketProduct:basket.getBasketProducts()) {
            BasketProductDto basketProductDto=new BasketProductDto();
            basketProductDto.setId(basketProduct.getId());
            basketProductDto.setProductId(basketProduct.getProduct().getId());
            basketProductDto.setCount(basketProduct.getCount());
            basketProductDto.setTotalPrice(basketProduct.getTotalPrice());
            basketProductDto.setBasketId(basketProduct.getBasket().getId());
            basketProductDtos.add(basketProductDto);
        }
        BasketDto dto= new BasketDto();
        dto.setId(basket.getId());
        dto.setStatus(basket.getStatus());
        dto.setTotalPrice(basket.getTotalPrice());
        dto.setUserId(basket.getUser().getId());
       dto.setBasketProducts(basketProductDtos);

       return dto;
    }

    private Basket toEntity(BasketDto basketDto){
        List<BasketProduct> basketProducts= new ArrayList<>();
        BasketProduct product=new BasketProduct(basketDto.getProductId() , basketDto.getCount());
        basketProducts.add(product);
        Basket basket=new Basket();
       basket.setUser(userService.findById(basketDto.getUserId()));
       basket.setBasketProducts(basketProducts);
        return  basket;
    }
}
