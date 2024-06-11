package com.shoppingwebapp.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoppingwebapp.Model.Product;

public interface ProductRepository1 extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.type = :type")
    List<Product> findProductsByType(@Param("type") String type);
	
	@Query("SELECT p FROM Product p WHERE p.tag = :tag")
    List<Product> findProductsByTag(@Param("tag") String tag);

}