package com.shoppingwebapp.Service;

import java.util.List;
import com.shoppingwebapp.Model.Product;

public interface ProductService {
	List<Product> findAll();

    Product findById(int id);

    Product save(Product product);
    
    void deleteByID(Integer id);
}
