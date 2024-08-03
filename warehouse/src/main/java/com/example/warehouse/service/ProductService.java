package com.example.warehouse.service;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.WarehouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product, Long warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        warehouse.ifPresent(product::setWarehouse);
        return productRepository.save(product);
    }

    // same function name cause i need it in AdminController
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
            existingProduct.setWarehouse(product.getWarehouse());
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByWarehouse(Long warehouseId) {
        return productRepository.findByWarehouseId(warehouseId);
    }

//    public List<Product> getProductsByLocation(String location) {
//        return productRepository.findByWarehouseLocationAndQuantityGreaterThan(location, 0);
//    }
    public List<Product> getProductsByLocation(String location) {
        List<Product> products = productRepository.findByWarehouse_Location(location);
        return products.stream()
            .filter(product -> product.getQuantityInStock() > 0)
            .collect(Collectors.toList());
}


    public void placeOrder(OrderEntity order) {
        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getQuantityInStock() >= order.getQuantity()) {
            // Update the stock
            product.setQuantityInStock(product.getQuantityInStock() - order.getQuantity());
            productRepository.save(product);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Insufficient stock available.");
        }
    }
}
