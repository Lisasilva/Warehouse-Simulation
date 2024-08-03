package com.example.warehouse.service;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        return warehouseRepository.findById(id).map(existingWarehouse -> {
            existingWarehouse.setLocation(warehouse.getLocation());
            existingWarehouse.setCapacity(warehouse.getCapacity());
            existingWarehouse.setQuantity(warehouse.getQuantity());
            existingWarehouse.setProduct(warehouse.getProduct());
            return warehouseRepository.save(existingWarehouse);
        }).orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
