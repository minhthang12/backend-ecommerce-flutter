package com.example.Ecommerce_SellPhone.repository;


import com.example.Ecommerce_SellPhone.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer> {
}
