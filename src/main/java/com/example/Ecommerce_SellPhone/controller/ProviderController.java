package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @GetMapping("/")
    public ResponseEntity<List<Provider>> getAllProvider() {
        List<Provider> provider = providerService.getAllProvider();
        return new ResponseEntity<>(provider,HttpStatus.OK);
    }

    @GetMapping("/{provider_id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable("provider_id") int provider_id){
        Provider provider = providerService.getProviderById(provider_id);
        return new ResponseEntity<>(provider,HttpStatus.OK);
    }
}
