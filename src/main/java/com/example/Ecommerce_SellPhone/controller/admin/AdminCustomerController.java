package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    @Autowired
    private CustomerService customerService;
//    @PostMapping("/update/{token}")
//    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("token")String token,
//                                                      @RequestParam("customer_id") int customer_id,
//                                                      @RequestParam("name") String name,
//                                                      @RequestParam("email") String email
//                                                      ){
//        customerService.updateCustomer(customer,customer_id,email,name);
//        return new ResponseEntity<>(new ApiResponse(true,"updated success"), HttpStatus.CREATED);
//    }
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllUser(){
        List<Customer> customerList = customerService.getAllUser();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
//    @GetMapping("/getSingleCustomer")
//    public ResponseEntity<Customer> getSingleCustomer(@RequestParam("customer_id")int customer_id){
//        Customer customer = customerService.getSingleCustomer(customer_id);
//        return new ResponseEntity<>(customer,HttpStatus.OK);
//    }
}
