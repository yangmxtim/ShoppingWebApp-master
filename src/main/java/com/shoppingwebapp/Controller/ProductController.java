// package com.shoppingwebapp.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.shoppingwebapp.Dao.ProductRepository;
// import com.shoppingwebapp.Model.Product;

// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "http://localhost:5173")
// @RestController
// @RequestMapping(path = "/product")
// public class ProductController {
//     private final ProductRepository productRepository;

//     @Autowired
//     public ProductController(ProductRepository productRepository) {
//         this.productRepository = productRepository;
//     }

//     /*
//      * @PostMapping(path = "/create")
//      * public String createProduct(@RequestParam String name, @RequestParam String
//      * description, @RequestParam String quantity, @RequestParam String
//      * price, @RequestParam("file") MultipartFile image) {
//      * Product product;
//      * try {
//      * product = new Product(name, description, quantity, price, image.getBytes());
//      * }
//      * catch (Exception e) {
//      * return "Constructing Fail!";
//      * }
//      * productRepository.save(product);
//      * return "Success!";
//      * }
//      */

//     @GetMapping(path = "/showProduct")
//     public Iterable<Product> listProduct() {
//         return productRepository.findAll();
//     }

//     @GetMapping(path = "/showProduct/{page}")
//     public Iterable<Product> listProductByPage(@PathVariable("page") int page) {
//         int elementInOnePage = 6;
//         Pageable pageable = PageRequest.of(page, elementInOnePage);
//         return productRepository.findAll(pageable).getContent();
//     }

//     @GetMapping(path = "/search")
//     public Iterable<Product> listProductByPage(@RequestParam String name) {
//         return productRepository.findByNameContaining(name);
//     }

// }
