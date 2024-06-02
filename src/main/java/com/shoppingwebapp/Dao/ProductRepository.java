package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;   


public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Iterable<Product> findByNameContaining(String name);
}
