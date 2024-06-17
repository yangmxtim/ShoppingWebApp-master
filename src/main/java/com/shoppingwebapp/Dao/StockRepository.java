package com.shoppingwebapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingwebapp.Model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
