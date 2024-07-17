package com.example.warehouse.service;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            existingWarehouse.setProduct(warehouse.getProduct());
            existingWarehouse.setQuantity(warehouse.getQuantity());
            return warehouseRepository.save(existingWarehouse);
        }).orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}

//package com.example.warehouse.service;
//
//import com.example.warehouse.model.Warehouse;
//import com.example.warehouse.repository.WarehouseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class WarehouseService {
//    @Autowired
//    private WarehouseRepository warehouseRepository;
//
//    public List<Warehouse> getAllWarehouses() {
//        return warehouseRepository.findAll();
//    }
//
//    public Warehouse getWarehouseById(Long id) {
//        return warehouseRepository.findById(id).orElse(null);
//    }
//
//    public Warehouse saveWarehouse(Warehouse warehouse) {
//        return warehouseRepository.save(warehouse);
//    }
//
//    //logic from stakoverflow to eliminate the usage of setId()
//    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
//        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(id);
//        if (existingWarehouse.isPresent()) {
//            Warehouse updatedWarehouse = existingWarehouse.get();
//            updatedWarehouse.setLocation(warehouse.getLocation());
//            updatedWarehouse.setCapacity(warehouse.getCapacity());
//            updatedWarehouse.setProduct(warehouse.getProduct());
//            updatedWarehouse.setQuantity(warehouse.getQuantity());
//            return warehouseRepository.save(updatedWarehouse);
//        } else {
//            throw new RuntimeException("Warehouse not found");
//        }
//    }
//
//
//
//    public void deleteWarehouse(Long id) {
//        warehouseRepository.deleteById(id);
//    }
//}
