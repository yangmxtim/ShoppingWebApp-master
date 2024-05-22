package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    public Iterable<Product> findByNameContaining(String name);
}
