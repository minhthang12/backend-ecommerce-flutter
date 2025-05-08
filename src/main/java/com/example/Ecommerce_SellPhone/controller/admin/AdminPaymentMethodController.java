package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.PaymentMethod;
import com.example.Ecommerce_SellPhone.service.PaymentMethods.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/payment-methods")
public class AdminPaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;
    @GetMapping("/")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethodList = paymentMethodService.getAllPaymentMethod();
        return new ResponseEntity<>(paymentMethodList, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethodService.createPaymentMethods(paymentMethod);
        return new ResponseEntity<>(new ApiResponse(true,"create success"), HttpStatus.CREATED);
    }
}
