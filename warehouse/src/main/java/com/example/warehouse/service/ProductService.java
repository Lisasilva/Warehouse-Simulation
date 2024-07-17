package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setQuantityInStock(product.getQuantityInStock());
            existingProduct.setSupplier(product.getSupplier());
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

//package com.example.warehouse.service;
//
//import com.example.warehouse.model.Product;
//import com.example.warehouse.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product getProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    public Product saveProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    //logic from stakoverflow to eliminate the usage of setId()
//
//    public Product updateProduct(Long id, Product product) {
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            Product updatedProduct = existingProduct.get();
//            updatedProduct.setName(product.getName());
//            updatedProduct.setDescription(product.getDescription());
//            updatedProduct.setPrice(product.getPrice());
//            updatedProduct.setCategory(product.getCategory());
//            updatedProduct.setQuantityInStock(product.getQuantityInStock());
//            updatedProduct.setSupplier(product.getSupplier());
//            return productRepository.save(updatedProduct);
//        } else {
//            throw new RuntimeException("Product not found");
//        }
//    }
//
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//}