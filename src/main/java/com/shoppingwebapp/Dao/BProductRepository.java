package com.shoppingwebapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoppingwebapp.Model.Product;

import jakarta.transaction.Transactional;

public interface BProductRepository extends JpaRepository<Product, Integer> {

	@Transactional
	@Query("SELECT count(name) FROM Product WHERE name = :name")
	Integer getProductSameNameCount(@Param(value = "name") String name);
}
