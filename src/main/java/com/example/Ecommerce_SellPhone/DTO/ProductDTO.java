package com.example.Ecommerce_SellPhone.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private Integer id;
    private String productName;
    private int price;
    private String productDescription;
    private String pictures;
    private String size; // Thêm thuộc tính size
    private Integer categoryId;
    private Integer providerId;

    public ProductDTO() {
    }

    // Getters and Setters


}
