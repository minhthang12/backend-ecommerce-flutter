package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> getOrdersByCustomer(Customer customer);
    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails od LEFT JOIN FETCH od.product WHERE o.customer = :customer AND o.status = :status")
    List<Order> findOrdersWithDetailsByCustomerAndStatus(@Param("customer") Customer customer, @Param("status") OrderStatus status);
}
