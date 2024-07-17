package com.example.warehouse.service;

import com.example.warehouse.model.Supplier;
import com.example.warehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier saveSupplier(Supplier supplier) {
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



//package com.example.warehouse.service;
//
//import com.example.warehouse.model.Supplier;
//import com.example.warehouse.repository.SupplierRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SupplierService {
//    @Autowired
//    private SupplierRepository supplierRepository;
//
//    public List<Supplier> getAllSuppliers() {
//        return supplierRepository.findAll();
//    }
//
//    public Supplier getSupplierById(Long id) {
//        return supplierRepository.findById(id).orElse(null);
//    }
//
//    public Supplier saveSupplier(Supplier supplier) {
//        return supplierRepository.save(supplier);
//    }
//
//    //logic from stakoverflow to eliminate the usage of setId()
//    public Supplier updateSupplier(Long id, Supplier supplier) {
//        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
//        if (existingSupplier.isPresent()) {
//            Supplier updatedSupplier = existingSupplier.get();
//            updatedSupplier.setName(supplier.getName());
//            updatedSupplier.setContactInfo(supplier.getContactInfo());
//            updatedSupplier.setAddress(supplier.getAddress());
//            return supplierRepository.save(updatedSupplier);
//        } else {
//            throw new RuntimeException("Supplier not found");
//        }
//    }
//
//
//    public void deleteSupplier(Long id) {
//        supplierRepository.deleteById(id);
//    }
//}
