package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestHeader("Authorization") String jwt,
                                                   @RequestBody CartDTO cartDTO,
                                                   @RequestParam("payment_method") String payment_method,
                                                   @RequestParam("address") String address) {
        Customer customer = customerService.findCustomerByJwt(jwt);
        orderService.createOrder(customer, cartDTO, payment_method, address);
        return new ResponseEntity<>(new ApiResponse(true, "order has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> getAllOrder(@RequestHeader("Authorization") String jwt) {
        Customer customer = customerService.findCustomerByJwt(jwt);
        List<Order> order = orderService.getAllOrder(customer);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
