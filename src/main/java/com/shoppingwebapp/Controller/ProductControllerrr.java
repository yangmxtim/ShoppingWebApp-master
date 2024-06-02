package com.shoppingwebapp.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RestController
public class ProductControllerrr {
	
	@Autowired
	private ProductService productService;

	public ProductControllerrr(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/product")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@PostMapping("/product")
	public Product createproduct(@RequestBody Product product) {
		product.setProduct_id(0);
		Product dbProduct = productService.save(product);
        return dbProduct;
	}
	
	@GetMapping("/product/{productId}")
	public Product getproduct(@PathVariable Integer productId){
		Product product = productService.findById(productId);
        if(product == null){
            throw new RuntimeException("note not found");
        }else{
            return product;
        }
    }
	
	@PutMapping("/product/{productId}")
	public Product updateproduct(@PathVariable Integer productId,
						 		@RequestBody Product product) {	
		Product dbProduct = productService.save(product);
        return dbProduct;
	}
	
	@DeleteMapping("/product/{productId}")
	public void deleteproduct(@PathVariable Integer productId) {	
		Product product = productService.findById(productId);
        if(product == null){
            throw new RuntimeException("note not found");
        }else{
        	productService.deleteByID(productId);
        }
	}
	
	
}
