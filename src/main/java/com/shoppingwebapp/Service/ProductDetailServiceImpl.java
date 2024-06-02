package com.shoppingwebapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingwebapp.Dao.ProductDetailRepository;
import com.shoppingwebapp.Model.Product_detail;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	private ProductDetailRepository productDetailRepository;
		
	@Autowired	
	public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
		this.productDetailRepository = productDetailRepository;
	}

	@Override
	public List<Product_detail> findAll() {
		return productDetailRepository.findAll();
	}

	@Override
	public Product_detail findById(int id) {
		Optional<Product_detail> productdetaillist = productDetailRepository.findById(id); 
		Product_detail product_detail = null;
        if(productdetaillist.isPresent()) {
        	product_detail = productdetaillist.get();
        }else{
            throw new RuntimeException("not found");
        }
        return product_detail;
	}
	
	@Transactional
	@Override
	public Product_detail save(Product_detail product_detail) {
		return productDetailRepository.save(product_detail);
	}
	
	 @Override
	    public void deleteByID(Integer id) {
		 productDetailRepository.deleteById(id);
	    }
	 
	 
	
}
