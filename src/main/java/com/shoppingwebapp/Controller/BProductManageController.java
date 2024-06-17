package com.shoppingwebapp.Controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingwebapp.DTO.ProductManageSummary;
import com.shoppingwebapp.Dao.ProductRepository;
import com.shoppingwebapp.Dao.StockRepository;
import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Model.Product_detail;
import com.shoppingwebapp.Model.Stock;
import com.shoppingwebapp.Service.ProductDetailService;
import com.shoppingwebapp.Service.ProductService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class BProductManageController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@Autowired
	private StockRepository stockRe;

	@GetMapping(path = "/")
	@ResponseBody
	public List<Product> findAll() {
		System.out.println("BProductManageController: findAll()");
		List<Product> product = productRepository.findAll();
		return product;
	}

	@GetMapping(path = "/getDetailFromProductId/{id}")
	@ResponseBody
	public Optional<Product> getDetailFromProductId(@PathVariable Integer id) {
		System.out.println("BProductManageController: getDetailFromProductId()");
		System.out.println(id);
		Optional<Product> product = productRepository.findById(id);
		System.out.println(product);
		return product;
	}

	// 按照ID刪除Product
	@Transactional
	@DeleteMapping(path = "/productManage/{id}")
	@ResponseBody
	public ResponseEntity<String> DeleteById(@PathVariable Integer id) {
		if (productRepository.findById(id).isEmpty()) {
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		} else {
			productRepository.deleteById(id);
		}
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
	}

	// 搜尋全部，自訂搜尋並排序分類
	@ResponseBody
	@PostMapping("/productManage/{page}")
	public ArrayList<Object> searchByMultiStr(@RequestParam(value = "searchText") String searchText,
			@RequestParam(value = "category") String category, @RequestParam(value = "orderBy") String order,
			@PathVariable(value = "page") Integer page) {

		System.out.println("BProductManageController: searchByMultiStr()");
		System.out.println(searchText + category + order);

		if (order.equals("價格最低")) {
			order = "minPrice ASC";
		} else if (order.equals("評分最高")) {
			order = "avgRate DESC";
		}

		if (category.equals("所有類別")) {
			category = "";
		}
		System.out.println("orderBy : " + order);

		int pageSize = 10;
		int offset = pageSize * (page - 1);

		String categoryLike = "%" + category + "%";
		String searchLike = "%" + searchText + "%";

		ArrayList<Object> responseList = new ArrayList<>();
		String sqlTotalRow = "SELECT count(*) FROM `product` WHERE name LIKE '" + searchLike + "' AND type LIKE '"
				+ categoryLike + "'";

		System.out.println(searchText + category + order);
		System.out.println(sqlTotalRow);

		String sql = "SELECT x.id, x.name, x.img, x.minPrice, x.maxPrice," + "x.type, y.avgRate, y.commentCount\r\n"
				+ "FROM (\r\n" + "    SELECT \r\n" + "        p.product_id AS id, \r\n" + "        p.name, \r\n"
				+ "        p.img, \r\n" + "        MIN(pd.price) AS minPrice, \r\n"
				+ "        MAX(pd.price) AS maxPrice, \r\n" + "        p.type\r\n" + "    FROM product p\r\n"
				+ "    LEFT JOIN product_detail pd ON p.product_id = pd.product_id\r\n"
				+ "    GROUP BY p.product_id, p.name, p.img, p.type\r\n" + ") AS x\r\n" + "LEFT JOIN (\r\n"
				+ "    SELECT \r\n" + "        p.product_id AS id,\r\n" + "        AVG(c.rate) AS avgRate, \r\n"
				+ "        COUNT(c.comment_id) AS commentCount\r\n" + "    FROM product p\r\n"
				+ "    LEFT JOIN comment c ON p.product_id = c.product_id\r\n" + "    GROUP BY p.product_id\r\n"
				+ ") AS y ON x.id = y.id" + " WHERE type LIKE ? AND name LIKE ? \r\n" + "ORDER BY " + order + " LIMIT "
				+ pageSize + " OFFSET " + offset;

		System.out.println("sqlTotalNum = " + sqlTotalRow);
		Integer totalNum = jdbcTemplate.queryForObject(sqlTotalRow, Integer.class);

		Object[] args = { categoryLike, searchLike };
		int[] argTypes = { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR };

		List<ProductManageSummary> productManageSummary = jdbcTemplate.query(sql, args, argTypes,
				new BeanPropertyRowMapper<>(ProductManageSummary.class));

		for (ProductManageSummary p : productManageSummary) {
			System.out.println(p.toString());
		}

		responseList.add(totalNum);
		responseList.add(productManageSummary);

		return responseList;
	}

	// 新增商品
	@Transactional
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(
			@RequestParam("name") String name, 
			@RequestParam("stock") Integer stock, 
			@RequestParam("type") String type, 
			@RequestParam("phone") String phone,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam("addr") String addr,
			@RequestParam(value = "facility", required = false) String facility,
			@RequestPart(value = "images", required = true) MultipartFile[] images,
			@RequestParam("intro") String intro,
			
			@RequestParam("detailName") String detailName, 
			@RequestParam("price") Integer price,
			@RequestParam(value = "specification", required = false) String specification,
			@RequestPart(value = "detailImg", required = true) MultipartFile[] detailImg,
			@RequestParam(value = "detailIntro", required = false) String detailIntro
	) {
		System.out.println("upload");
		System.out.println(images);
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
		if (detailImg != null) {
			for (MultipartFile image : detailImg) {
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
		System.out.println(imgDataUris);
		switch (type) {
		case "transport":
			type = "交通";
			break;
		case "stay":
			type = "住宿";
			break;
		case "tourist":
			type = "景點門票";
			break;
		}

		Product product = new Product();
		product.setName(name);
		product.setType(type);
		product.setPhone(phone);
		product.setTag(tag);
		product.setAddress(addr);
		product.setFacility(facility);
		product.setType(type);
		product.setIntroduction(intro);
		if (!imgDataUris.isEmpty()) {
			product.setImg(imgDataUris.get(0));
		}
		product = productService.save(product);

		Product_detail productDetail = new Product_detail();
		productDetail.setName(detailName);
		productDetail.setPrice(price);
		productDetail.setSpecification(specification);
		productDetail.setIntroduction(detailIntro);

		if (imgDataUris.size() > 1) {
			productDetail.setImg(imgDataUris.get(1));
		}
		productDetail.setProduct(product);
		productDetailService.save(productDetail);

//		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		
		Stock stockBean = new Stock();
		stockBean.setQuantity(10);
		stockBean.setProduct_detail(productDetail);
		stockRe.save(stockBean);

		return ResponseEntity.ok("File uploaded and data saved successfully");
	}
}