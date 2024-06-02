package com.shoppingwebapp.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingwebapp.Model.Product_detail;
import com.shoppingwebapp.Service.ProductDetailService;
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
public class ProductDetailController {
	
	@Autowired
	private ProductDetailService productDetailService;

	public ProductDetailController(ProductDetailService productDetailService) {
		this.productDetailService = productDetailService;
	}
	
	@GetMapping("/productdetail")
	public List<Product_detail> findAll(){
		return productDetailService.findAll();
	}
	
	@PostMapping("/productdetail")
	public Product_detail createproductdetail(@RequestBody Product_detail product_detail) {
		product_detail.setProduct_detail_id(0);
		Product_detail dbProductDetail = productDetailService.save(product_detail);
        return dbProductDetail;
	}
	
	@GetMapping("/productdetail/{Product_detail_id}")
	public Product_detail getproductdetail(@PathVariable Integer Product_detail_id){
		Product_detail product_detail = productDetailService.findById(Product_detail_id);
        if(product_detail == null){
            throw new RuntimeException("note not found");
        }else{
            return product_detail;
        }
    }
	
	@PutMapping("/productdetail/{Product_detail_id}")
	public Product_detail updateproductdetail(@PathVariable Integer Product_detail_id,
						 		@RequestBody Product_detail product_detail) {	
		Product_detail dbProductDetail = productDetailService.save(product_detail);
        return dbProductDetail;
	}
	
	@DeleteMapping("/productdetail/{Product_detail_id}")
	public void deleteproductdetail(@PathVariable Integer Product_detail_id) {	
		Product_detail product_detail = productDetailService.findById(Product_detail_id);
        if(product_detail == null){
            throw new RuntimeException("note not found");
        }else{
        	productDetailService.deleteByID(Product_detail_id);
        }
	}
}
