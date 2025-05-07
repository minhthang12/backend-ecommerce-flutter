package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.OrderStatus;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import com.example.Ecommerce_SellPhone.service.OrderDetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/")
    public ResponseEntity<List<Order_Details>> getAllOrder(@RequestHeader("Authorization") String jwt,
                                                   @RequestParam("order_id")int order_id) {
        Customer customer = customerService.findCustomerByJwt(jwt);
        List<Order_Details> orderDetails = orderDetailsService.getAllOrderDetails(customer,order_id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    @GetMapping("/status")
    public ResponseEntity<List<Order_Details>> getOrderDetailsByStatus(
            @RequestHeader("Authorization") String jwt,
            @RequestParam("status") OrderStatus status
            ) {
        Customer customer = customerService.findCustomerByJwt(jwt);
        List<Order_Details> orderDetails = orderDetailsService.getOrderDetailsByStatus(customer, status);
        return ResponseEntity.ok(orderDetails);
    }
}
