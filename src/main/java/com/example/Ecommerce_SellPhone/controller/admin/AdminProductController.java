package com.example.Ecommerce_SellPhone.controller.admin;

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

import java.util.Optional;

@RestController
@RequestMapping("/admin/product")

public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
            }
        }
        if (productDTO.getProviderId() != null) {
            Optional<Provider> optionalProvider = providerRepository.findById(productDTO.getProviderId());
            if (!optionalProvider.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "Provider does not exist"), HttpStatus.BAD_REQUEST);
            }
        }
        productService.createProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDTO productDTO){
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if(!optionalProduct.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"product does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);
    }
}
