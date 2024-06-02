package com.shoppingwebapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingwebapp.Model.Product_detail;

public interface ProductDetailRepository extends JpaRepository<Product_detail, Integer> {
	
}
