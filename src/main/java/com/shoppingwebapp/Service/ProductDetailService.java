package com.shoppingwebapp.Service;

import java.util.List;
import com.shoppingwebapp.Model.Product_detail;

public interface ProductDetailService {
	List<Product_detail> findAll();

	Product_detail findById(int id);

    Product_detail save(Product_detail product_detail);
    
    void deleteByID(Integer id);
}
