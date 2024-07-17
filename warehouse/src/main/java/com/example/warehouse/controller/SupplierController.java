package com.example.warehouse.controller;

import com.example.warehouse.model.Supplier;
import com.example.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    // this method makes more sense
    // @PutMapping("/{id}")
    // public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
    //     supplier.setId(id);
    //     return supplierService.saveSupplier(supplier);
    // }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
}


//package com.example.warehouse.controller;
//
//import com.example.warehouse.model.Product;
//import com.example.warehouse.model.Supplier;
//import com.example.warehouse.service.SupplierService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/suppliers")
//public class SupplierController {
//    @Autowired
//    private SupplierService supplierService;
//
//    @GetMapping
//    public List<Supplier> getAllSuppliers() {
//        return supplierService.getAllSuppliers();
//    }
//
//    @GetMapping("/{id}")
//    public Supplier getSupplierById(@PathVariable Long id) {
//        return supplierService.getSupplierById(id);
//    }
//
//    @PostMapping
//    public Supplier createSupplier(@RequestBody Supplier supplier) {
//        return supplierService.saveSupplier(supplier);
//    }
//
////    @PutMapping("/{id}")
////    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
////        supplier.setId(id);
////        return supplierService.saveSupplier(supplier);
////    }
//
//
//    @PutMapping("/{id}")
//    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
//        return supplierService.updateSupplier(id, supplier);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteSupplier(@PathVariable Long id) {
//        supplierService.deleteSupplier(id);
//    }
//}
