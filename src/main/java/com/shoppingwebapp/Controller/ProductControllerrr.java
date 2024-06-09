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
	
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("name") String name, @RequestParam("price") Integer price,
			@RequestParam("stock") Integer stock, @RequestParam("type") String type, @RequestParam("tag") String tag,
			@RequestParam(value = "fromTime", required = false) String fromTime,
			@RequestParam(value = "toTime", required = false) String toTime,
			@RequestParam(value = "fromPlace", required = false) String fromPlace,
			@RequestParam(value = "toPlace", required = false) String toPlace,
			@RequestParam(value = "addr", required = false) String addr, @RequestParam("intro") String intro,
			@RequestPart(value = "images", required = false) MultipartFile[] images) {
		// 印出表單數據和文件
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
		System.out.println("Stock: " + stock);
		System.out.println("Type: " + type);
		System.out.println("Tag: " + tag);
		System.out.println("From Time: " + fromTime);
		System.out.println("To Time: " + toTime);
		System.out.println("From Place: " + fromPlace);
		System.out.println("To Place: " + toPlace);
		System.out.println("Addr: " + addr);
		System.out.println("Intro: " + intro);

		List<String> imgDataUris = new ArrayList<>();

		if (images != null) {
			for (MultipartFile image : images) {
				try {
					byte[] bytes = image.getBytes();
					String base64 = Base64.getEncoder().encodeToString(bytes);
					String dataUri = "data:" + image.getContentType() + ";base64," + base64;
					System.out.println(dataUri);
					imgDataUris.add(dataUri);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(500).body("Failed to save image data");
				}
			}
		}

		Product product = new Product();
		product.setName(name);
		product.setAddress(addr);
		product.setType(type);
		product.setIntroduction(intro);

		if (!imgDataUris.isEmpty()) {
			// Assuming the first image is used for the product image
			product.setImg(imgDataUris.get(0));
		}

		product = productService.save(product);

		// 创建并保存ProductDetail实体
		Product_detail productDetail = new Product_detail();
//		productDetail.setTag(tag);
		productDetail.setPrice(price);
//		productDetail.setQuantity(stock);
//		productDetail.setDeparture_location(fromPlace);
//		productDetail.setDestination_location(toPlace);


		if (imgDataUris.size() > 1) {
			// Assuming the second image is used for the product detail image
			productDetail.setImg(imgDataUris.get(1));
		}
		
	    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

		if (fromTime != null) {
	        LocalDate departureDate = LocalDate.parse(fromTime, formatter);
//	        productDetail.setDeparture_time(Date.valueOf(departureDate));
	    }
	    if (toTime != null) {
	        LocalDate arriveDate = LocalDate.parse(toTime, formatter);
//	        productDetail.setArrive_time(Date.valueOf(arriveDate));
	    }

	    if (imgDataUris.size() > 1) {
	        // Assuming the second image is used for the product detail image
	        productDetail.setImg(imgDataUris.get(1));
	    }

		productDetail.setProduct(product);

		productDetailService.save(productDetail);

		return ResponseEntity.ok("File uploaded and data saved successfully");
	}
	
	
}
