package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.CategoryRepository;
import com.example.Ecommerce_SellPhone.repository.ProductRepository;
import com.example.Ecommerce_SellPhone.repository.ProviderRepository;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/{Category_ID}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(@PathVariable("Category_ID") int Category_ID) {
        List<ProductDTO> productDTOList = productService.getAllProductByCategory(Category_ID);
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProduct(@RequestParam("product_name") String product_name){
        List<ProductDTO> productDTOList = productService.searchProduct(product_name);
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);    }
    @GetMapping("/singleProduct/{product_id}")
    public ProductDTO getSingleProduct(@PathVariable("product_id") int product_id){
        ProductDTO productDTO = productService.FindByID(product_id);
        return productDTO;
    }
    @GetMapping("/provider/{provider_id}")
    public ResponseEntity<List<ProductDTO>> getProductByProvider_ID(@PathVariable("provider_id") int provider_id){
        List<ProductDTO> productDTOList = productService.getAllProductByProviderID(provider_id);
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
}
