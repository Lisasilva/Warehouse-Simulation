package com.example.warehouse.service;

import com.example.warehouse.model.Customer;
import com.example.warehouse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}


//package com.example.warehouse.service;
//
//import com.example.warehouse.model.Customer;
//import com.example.warehouse.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    public Customer getCustomerById(Long id) {
//        return customerRepository.findById(id).orElse(null);
//    }
//
//    public Customer saveCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//
//    //logic from stakoverflow to eliminate the usage of setId()
//
//    public Customer updateCustomer(Long id, Customer customer) {
//        Optional<Customer> existingCustomer = customerRepository.findById(id);
//        if (existingCustomer.isPresent()) {
//            Customer updatedCustomer = existingCustomer.get();
//            updatedCustomer.setName(customer.getName());
//            updatedCustomer.setEmail(customer.getEmail());
//            updatedCustomer.setPhone(customer.getPhone());
//            updatedCustomer.setAddress(customer.getAddress());
//            return customerRepository.save(updatedCustomer);
//        } else {
//            throw new RuntimeException("Customer not found");
//        }
//    }
//
//    public void deleteCustomer(Long id) {
//        customerRepository.deleteById(id);
//    }
//}
//
//
//
//
