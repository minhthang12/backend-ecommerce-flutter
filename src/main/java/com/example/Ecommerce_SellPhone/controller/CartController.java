package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.AddToCartDTO;
import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Cart;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Wishlist;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Cart.CartService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO,
                                                 @RequestHeader("Authorization") String jwt){
        Customer customer = customerService.findCustomerByJwt(jwt);
        cartService.addToCart(addToCartDTO,customer);
        return new ResponseEntity<>(new ApiResponse(true,"Added to Cart"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<CartDTO> getCartItems(@RequestHeader("Authorization") String jwt){
        Customer customer = customerService.findCustomerByJwt(jwt);
        CartDTO cartDTO = cartService.listCartItems(customer);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCartItem(@RequestParam("cartItemId") int cartItemId,
                                                      @RequestHeader("Authorization") String jwt){
        Customer customer = customerService.findCustomerByJwt(jwt);
        cartService.deleteCartItem(cartItemId,customer);
        return new ResponseEntity<>(new ApiResponse(true,"Items has been removed"), HttpStatus.OK);
    }
    @GetMapping("/check/{token}")
    public ResponseEntity<Boolean> checkWishlist(@RequestHeader("Authorization") String jwt,
                                                 @RequestParam("product_id") int product_id){
        Customer customer = customerService.findCustomerByJwt(jwt);
        Cart existingCartEntry = cartService.checkCart(customer, product_id);
        if (existingCartEntry != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
    @PostMapping("/update/increase")
    public ResponseEntity<ApiResponse> updateQuantityIncrease(@RequestHeader("Authorization") String jwt,
                                                              @RequestParam("product_id") int product_id){
        Customer customer = customerService.findCustomerByJwt(jwt);
        cartService.updateQuantityIncrease(customer,product_id);
        return new ResponseEntity<>(new ApiResponse(true,"Updated Quantity success"), HttpStatus.OK);
    }
    @PostMapping("/update/decrease")
    public ResponseEntity<ApiResponse> updateQuantityDecrease(@RequestHeader("Authorization") String jwt,
                                                              @RequestParam("product_id") int product_id){
        Customer customer = customerService.findCustomerByJwt(jwt);
        cartService.updateQuantityDecrease(customer,product_id);
        return new ResponseEntity<>(new ApiResponse(true,"Updated Quantity success"), HttpStatus.OK);
    }
}
