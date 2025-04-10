package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> category = categoryService.getAllCategory();
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") int category_id){
        Category category = categoryService.getCategoryById(category_id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

}
