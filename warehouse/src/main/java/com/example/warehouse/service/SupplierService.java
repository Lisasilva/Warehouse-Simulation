package com.example.warehouse.service;

import com.example.warehouse.model.Supplier;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.SupplierRepository;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier createSupplier(Supplier supplier, Long warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        warehouse.ifPresent(supplier::setWarehouse);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getSuppliersByWarehouse(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return supplierRepository.findByWarehouse(warehouse);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplier) {
        return supplierRepository.findById(id).map(existingSupplier -> {
            existingSupplier.setName(supplier.getName());
            existingSupplier.setContactInfo(supplier.getContactInfo());
            existingSupplier.setAddress(supplier.getAddress());
            return supplierRepository.save(existingSupplier);
        }).orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
