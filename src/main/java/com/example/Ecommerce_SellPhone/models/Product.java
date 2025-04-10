package com.example.Ecommerce_SellPhone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;
    private int price;
    private String productDescription;
    private String pictures;
    private String size;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Product() {
    }

    public Product(Integer id, String productName, int price, String productDescription, String pictures, String size, Category category, Provider provider) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
        this.pictures = pictures;
        this.size = size;
        this.category = category;
        this.provider = provider;
    }
}
