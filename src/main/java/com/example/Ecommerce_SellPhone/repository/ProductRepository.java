package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findAllByProviderId(int providerId);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchProduct(@Param("name") String name);
}
