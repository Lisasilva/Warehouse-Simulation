package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.model.Supplier;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.service.OrderService;
import com.example.warehouse.service.WarehouseService;
import com.example.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private WarehouseService warehouseService;  // NOT USED?

    @GetMapping("/products")
        public List<Product> getAllProducts(@RequestParam Long warehouseId) {
        return productService.getProductsByWarehouse(warehouseId);
    }

//    @GetMapping("/products")
//    public List<Product> getAllProducts(@RequestParam Long warehouseId) {
//        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
//        return productService.getProductsByWarehouse(warehouse);
//    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product, @RequestParam Long warehouseId) {
        // Ensuring that the warehouse exists and assign it to the product
        return productService.saveProduct(product, warehouseId);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders(@RequestParam Long warehouseId) {
        return orderService.getOrdersByWarehouse(warehouseId);
    }

    @GetMapping("/orders/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/orders/{id}")
    public OrderEntity updateOrder(@PathVariable Long id, @RequestBody OrderEntity order) {
        return orderService.updateOrder(id, order);
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(@RequestParam Long warehouseId) {
        return supplierService.getSuppliersByWarehouse(warehouseId);
    }

    @GetMapping("/suppliers/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping("/suppliers")
    public Supplier createSupplier(@RequestBody Supplier supplier,@RequestParam Long warehouseId) {
        // Ensure the warehouse exists and assign it to the supplier
        return supplierService.createSupplier(supplier, warehouseId);
    }

    @PutMapping("/suppliers/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/suppliers/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
}
