package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.service.FeaturedProduct.FeaturedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/featured")
public class AdminFeaturedController {
    @Autowired
    private FeaturedProductService featuredProductService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody Product product){
        if(featuredProductService.create(product)){
            return new ResponseEntity<>(new ApiResponse(true, "Create Success"), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new ApiResponse(false, "Failed"), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id){
        featuredProductService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted Success"), HttpStatus.OK);
    }
}
