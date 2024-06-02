package com.shoppingwebapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoppingwebapp.Dao.ProductRepository1;
import com.shoppingwebapp.Model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository1 productRepository;
		
	@Autowired	
	public ProductServiceImpl(ProductRepository1 productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int id) {
		Optional<Product> productlist = productRepository.findById(id); 
		Product product = null;
        if(productlist.isPresent()) {
        	product = productlist.get();
        }else{
            throw new RuntimeException("not found");
        }
        return product;
	}
	
	@Transactional
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	 @Override
	    public void deleteByID(Integer id) {
		 	productRepository.deleteById(id);
	    }
	 
	
}
