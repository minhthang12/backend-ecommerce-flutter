package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import com.example.Ecommerce_SellPhone.service.OrderDetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> order = orderService.getAllOrderAmin();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/details/{order_id}")
    public ResponseEntity<List<Order_Details>> getAllOrderDetails(@PathVariable("order_id") int order_id) {
        List<Order_Details> orderDetails = orderDetailsService.getOrderDetail_OrderID(order_id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
