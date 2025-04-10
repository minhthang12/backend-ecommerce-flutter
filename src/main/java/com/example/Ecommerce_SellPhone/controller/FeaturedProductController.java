package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.service.FeaturedProduct.FeaturedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/featured")
public class FeaturedProductController {
    @Autowired
    private FeaturedProductService featuredProductService;
    @GetMapping("/")
    public ResponseEntity<List<PopularProductList>> getAllProduct(){
        List<PopularProductList> popularProductLists = featuredProductService.getAllPopularProduct();
        return new ResponseEntity<>(popularProductLists, HttpStatus.OK);
    }
}
