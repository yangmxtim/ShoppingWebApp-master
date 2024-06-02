package com.shoppingwebapp.Dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingwebapp.Model.Product;

public interface ProductRepository1 extends JpaRepository<Product, Integer> {

}