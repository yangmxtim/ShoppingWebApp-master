package com.shoppingwebapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingwebapp.Dao.ProductRepository1;
import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Model.Product_detail;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository1 productRepository;

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
        Optional<Product> productOptional = productRepository.findById(id); 
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
    
    @Transactional
    @Override
    public Product save(Product product) {
        if (product.getProduct_id() != null && product.getProduct_id() > 0) {
            Optional<Product> existingProductOpt = productRepository.findById(product.getProduct_id());
            if (existingProductOpt.isPresent()) {
                Product existingProduct = existingProductOpt.get();
                updateProductDetails(existingProduct, product);
                return productRepository.save(existingProduct);
            } else {
                throw new RuntimeException("Product not found with id: " + product.getProduct_id());
            }
        } else {
            if (product.getProduct_detail() != null) {
                for (Product_detail detail : product.getProduct_detail()) {
                    detail.setProduct(product);
                }
            }
            return productRepository.save(product);
        }
    }

    private void updateProductDetails(Product existingProduct, Product newProduct) {
        existingProduct.setName(newProduct.getName());
        existingProduct.setImg(newProduct.getImg());
        existingProduct.setAddress(newProduct.getAddress());
        existingProduct.setType(newProduct.getType());
        existingProduct.setPhone(newProduct.getPhone());
        existingProduct.setIntroduction(newProduct.getIntroduction());

        // Clear existing details
        existingProduct.getProduct_detail().clear();

        // Add new details
        if (newProduct.getProduct_detail() != null) {
            for (Product_detail detail : newProduct.getProduct_detail()) {
                detail.setProduct(existingProduct);
                existingProduct.addProductDetail(detail);
            }
        }
    }

    @Override
    public void deleteByID(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
