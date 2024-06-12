// package com.shoppingwebapp.Controller;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.Base64;
// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.shoppingwebapp.Dao.ProductDetailRepository;
// import com.shoppingwebapp.Dao.ProductRepository;
// import com.shoppingwebapp.Dto.ProductManageSummary;
// import com.shoppingwebapp.Model.Product;
// import com.shoppingwebapp.Model.Product_detail;
// import com.shoppingwebapp.Model.Stock;
// import com.shoppingwebapp.Service.ProductService;

// import jakarta.persistence.EntityManager;
// import org.springframework.web.bind.annotation.RequestBody;

// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
// 		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
// @RestController
// public class BProductManageController {

// 	private ProductRepository productRepository;

// 	private ProductDetailRepository productDetailRepository;

// 	private EntityManager entityManager;

// 	private JdbcTemplate jdbcTemplate;

// 	private ProductService productService;

// 	public BProductManageController(ProductRepository productRepository,
// 			ProductDetailRepository productDetailRepository, EntityManager entityManager, JdbcTemplate jdbcTemplate,
// 			ProductService productService) {
// 		this.productRepository = productRepository;
// 		this.productDetailRepository = productDetailRepository;
// 		this.entityManager = entityManager;
// 		this.jdbcTemplate = jdbcTemplate;
// 		this.productService = productService;
// 	}

// 	@GetMapping(path = "/")
// 	@ResponseBody
// 	public List<Product> findAll() {
// 		System.out.println("BProductManageController: findAll()");
// 		List<Product> product = productRepository.findAll();
// 		return product;
// 	}

// 	// 按照ID刪除Product
// 	@Transactional
// 	@DeleteMapping(path = "/productManage/{id}")
// 	@ResponseBody
// 	public void DeleteById(@PathVariable Integer id) {
// 		System.out.println("BProductManageController");
// 		Product product = entityManager.find(Product.class, id);
// 		List<Stock> listStock = null;
// 		List<Product_detail> productDetails = product.getProduct_detail();
// 		for (Product_detail pd : productDetails) {
// 			pd.setProduct(null);
// 			listStock.addAll(pd.getStock());
// 			pd.getOrderItem().setOrder_detail(null);
// 		}
// 		for (Stock s : listStock) {
// 			s.setProduct_detail(null);
// 		}

// 		entityManager.remove(product);
// 	}

// 	// 搜尋全部，自訂搜尋並排序分類
// 	@ResponseBody
// 	@PostMapping("/productManage/{page}")
// 	public ArrayList<Object> searchByMultiStr(@RequestParam(value = "searchText") String searchText,
// 			@RequestParam(value = "category") String category, @RequestParam(value = "orderBy") String order,
// 			@PathVariable(value = "page") Integer page) {
		
// 		System.out.println("BProductManageController: searchByMultiStr()");
// 		System.out.println(searchText + category + order);
		

// 		if (order.equals("價格最低")) {
// 			order = "minPrice ASC";
// 		} else if (order.equals("評分最高")) {
// 			order = "avgRate DESC";
// 		}
		
// 		if (category.equals("所有類別")) {
// 			category = "";
// 		}
// 		System.out.println("orderBy : " + order);

// 		int pageSize = 10;
// 		int offset = pageSize * (page - 1);

// 		String categoryLike = "%" + category + "%";
// 		String searchLike = "%" + searchText + "%";

// 		ArrayList<Object> responseList = new ArrayList<>();
// 		String sqlTotalRow = "SELECT count(*) FROM `product` WHERE name LIKE '" + searchLike 
// 								+ "' AND type LIKE '"+ categoryLike + "'";

// 		System.out.println(searchText + category + order);
// 		System.out.println(sqlTotalRow);

// 		String sql = "SELECT * FROM\r\n"
// 				+ "(SELECT p.product_id as id, p.name, p.img, min(pd.price) as minPrice, max(pd.price) as maxPrice, p.type\r\n"
// 				+ "FROM product p " + "left join product_detail pd on p.product_id = pd.product_id\r\n"
// 				+ "group by id) as x LEFT JOIN\r\n"
// 				+ "(SELECT p.product_id as id, AVG(rate) as avgRate, count(c.comment_id) as commentCount\r\n"
// 				+ "FROM product p " + "inner join comment c on p.product_id = c.product_id\r\n"
// 				+ "group by id) as y " + "on x.id = y.id " + "WHERE type LIKE ? AND name LIKE ? \r\n"
// 				+ "ORDER BY "+ order + " LIMIT " + pageSize + " OFFSET " + offset;

// 		System.out.println("sqlTotalNum = " + sqlTotalRow);
// 		Integer totalNum = jdbcTemplate.queryForObject(sqlTotalRow, Integer.class);

// 		Object[] args = { categoryLike, searchLike };
// 		int[] argTypes = { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR };

// 		List<ProductManageSummary> productManageSummary = jdbcTemplate.query(sql, args, argTypes,
// 				new BeanPropertyRowMapper<>(ProductManageSummary.class));
		
// 		for(ProductManageSummary p : productManageSummary) {
// 			System.out.println(p.toString());
// 		}

// 		responseList.add(totalNum);
// 		responseList.add(productManageSummary);

// 		return responseList;
// 	}

// 	// 新增商品
// 	@PostMapping("/upload")
// 	public ResponseEntity<?> handleFileUpload(@RequestParam("name") String name, @RequestParam("price") Integer price,
// 			@RequestParam("stock") Integer stock, @RequestParam("type") String type, @RequestParam("tag") String tag,
// 			@RequestParam(value = "fromTime", required = false) String fromTime,
// 			@RequestParam(value = "toTime", required = false) String toTime,
// 			@RequestParam(value = "fromPlace", required = false) String fromPlace,
// 			@RequestParam(value = "toPlace", required = false) String toPlace,
// 			@RequestParam(value = "addr", required = false) String addr, @RequestParam("intro") String intro,
// 			@RequestPart(value = "images", required = false) MultipartFile[] images) {

// 		List<String> imgDataUris = new ArrayList<>();

// 		if (images != null) {
// 			for (MultipartFile image : images) {
// 				try {
// 					byte[] bytes = image.getBytes();
// 					String base64 = Base64.getEncoder().encodeToString(bytes);
// 					String dataUri = "data:" + image.getContentType() + ";base64," + base64;
// 					System.out.println(dataUri);
// 					imgDataUris.add(dataUri);
// 				} catch (Exception e) {
// 					e.printStackTrace();
// 					return ResponseEntity.status(500).body("Failed to save image data");
// 				}
// 			}
// 		}

// 		Product product = new Product();
// 		product.setName(name);
// 		product.setAddress(addr);
// 		product.setType(type);
// 		product.setIntroduction(intro);

// 		if (!imgDataUris.isEmpty()) {
// 			// Assuming the first image is used for the product image
// 			product.setImg(imgDataUris.get(0));
// 		}

// 		product = productService.save(product);

// 		// 创建并保存ProductDetail实体
// 		Product_detail productDetail = new Product_detail();
// //		productDetail.setTag(tag);
// 		productDetail.setPrice(price);
// //		productDetail.setQuantity(stock);
// //		productDetail.setDeparture_location(fromPlace);
// //		productDetail.setDestination_location(toPlace);

// 		if (imgDataUris.size() > 1) {
// 			// Assuming the second image is used for the product detail image
// 			productDetail.setImg(imgDataUris.get(1));
// 		}

// 		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

// 		if (fromTime != null) {
// 			LocalDate departureDate = LocalDate.parse(fromTime, formatter);
// //	        productDetail.setDeparture_time(Date.valueOf(departureDate));
// 		}
// 		if (toTime != null) {
// 			LocalDate arriveDate = LocalDate.parse(toTime, formatter);
// //	        productDetail.setArrive_time(Date.valueOf(arriveDate));
// 		}

// 		if (imgDataUris.size() > 1) {
// 			// Assuming the second image is used for the product detail image
// 			productDetail.setImg(imgDataUris.get(1));
// 		}

// 		productDetail.setProduct(product);

// //		productDetailService.save(productDetail);

// 		return ResponseEntity.ok("File uploaded and data saved successfully");
// 	}
// }