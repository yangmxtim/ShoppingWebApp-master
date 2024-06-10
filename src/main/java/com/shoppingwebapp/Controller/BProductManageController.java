package com.shoppingwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Dao.ProductDetailRepository;
import com.shoppingwebapp.Dao.ProductRepository;
import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Model.Product_detail;

import jakarta.persistence.EntityManager;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class BProductManageController {

    private ProductRepository productRepository;
    
    private ProductDetailRepository productDetailRepository;
	
	private EntityManager entityManager;
	
	public BProductManageController(ProductRepository productRepository,
			ProductDetailRepository productDetailRepository, EntityManager entityManager) {
		this.productRepository = productRepository;
		this.productDetailRepository = productDetailRepository;
		this.entityManager = entityManager;
	}

	@GetMapping(path = "/productManage") // Create member
    @ResponseBody
    public List<Product_detail> findAll() {
    	System.out.println("BProductManageController: findAll()");
    	List<Product_detail> product_details =  productDetailRepository.findAll();
		return product_details;
    }
    
//	@Transactional
    @DeleteMapping(path = "/productManage/{id}") // Create member
    @ResponseBody
    public void DeleteById(@PathVariable Integer id) {
    	System.out.println("BProductManageController");
    	Product_detail product_detail = entityManager.find(Product_detail.class, id);
    	Product product = product_detail.getProduct();
    	
    	entityManager.remove(product);
    	entityManager.remove(product_detail);
    	entityManager.close();
    	System.out.println("OK");
    }

}