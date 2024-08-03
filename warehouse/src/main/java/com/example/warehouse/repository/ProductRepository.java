package com.example.warehouse.repository;

import com.example.warehouse.model.Product;
import com.example.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByWarehouse(Warehouse warehouse);
    List<Product> findByWarehouse_Location(String location);
    //List<Product> findByWarehouseLocationAndQuantityGreaterThan(String location, int quantityInStock);
    List<Product> findByWarehouseId(Long warehouseId);

}
