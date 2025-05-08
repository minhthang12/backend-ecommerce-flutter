package com.example.Ecommerce_SellPhone.service.PaymentMethods;

import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.PaymentMethod;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    public List<PaymentMethod> getAllPaymentMethod(){
        List<PaymentMethod> paymentMethodLists = paymentMethodRepository.findAll();
        return paymentMethodLists;
    }
    public void createPaymentMethods(PaymentMethod paymentMethod) {
        PaymentMethod paymentMethodDB = new PaymentMethod();
        paymentMethodDB.setPayment_method_name(paymentMethod.getPayment_method_name());
        paymentMethodRepository.save(paymentMethodDB);
    }
}
