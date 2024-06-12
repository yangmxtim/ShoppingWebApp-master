package com.shoppingwebapp.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Model.Product_detail;
import com.shoppingwebapp.Service.ProductDetailService;
import com.shoppingwebapp.Service.ProductService;
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RestController
public class ProductControllerrr {
	
	@Autowired
	private ProductService productService;

	private ProductDetailService productDetailService;

	public ProductControllerrr(ProductService productService, ProductDetailService productDetailService) {
		this.productService = productService;
		this.productDetailService = productDetailService;
	}
	
	@GetMapping("/product")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		product.setProduct_id(0);
		Product dbProduct = productService.save(product);
        return dbProduct;
	}
	
	@GetMapping("/product/{productId}")
	public Product getProduct(@PathVariable Integer productId){
		Product product = productService.findById(productId);
        if(product == null){
            throw new RuntimeException("note not found");
        }else{
            return product;
        }
    }
	
	 @PutMapping("/product/{productId}")
	    public Product updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
	        product.setProduct_id(productId);
	        return productService.save(product);
	}
	
	@DeleteMapping("/product/{productId}")
	public void deleteProduct(@PathVariable Integer productId) {	
		Product product = productService.findById(productId);
        if(product == null){
            throw new RuntimeException("note not found");
        }else{
        	productService.deleteByID(productId);
        }
	}
	
	@GetMapping("/products")
    public List<Product> getProductsByType(@RequestParam String type) {
        return productService.getProductsByType(type);
    }
	
	@GetMapping("/tag")
    public List<Product> getProductsByTag(@RequestParam String tag) {
        return productService.getProductsByTag(tag);
    }
	
	
}
