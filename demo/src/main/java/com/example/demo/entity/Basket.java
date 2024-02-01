package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor



public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int status;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.DETACH)
    private List<BasketProduct> basketProducts;

    public Basket(Long id, int status, double totalPrice, User user, List<BasketProduct> basketProducts) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
        this.basketProducts = basketProducts;
    }

}
