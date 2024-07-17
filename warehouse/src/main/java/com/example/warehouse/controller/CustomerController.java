package com.example.warehouse.controller;

import com.example.warehouse.model.Customer;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.CustomerRepository;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer, @RequestParam String password) {
        Customer savedCustomer = customerRepository.save(customer);
        User user = new User();
        user.setUsername(savedCustomer.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        user.setCustomer(savedCustomer);
        userRepository.save(user);
        return savedCustomer;
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}


//package com.example.warehouse.controller;
//
//import com.example.warehouse.model.Customer;
//import com.example.warehouse.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomerController {
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping
//    public List<Customer> getAllCustomers() {
//        return customerService.getAllCustomers();
//    }
//
//    @GetMapping("/{id}")
//    public Customer getCustomerById(@PathVariable Long id) {
//        return customerService.getCustomerById(id);
//    }
//
//    @PostMapping
//    public Customer createCustomer(@RequestBody Customer customer) {
//        return customerService.saveCustomer(customer);
//    }
////    @PutMapping("/{id}")
////    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
////        customer.setId(id);
////        return customerService.saveCustomer(customer);
////    }
//
//    @PutMapping("/{id}")
//    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//        return customerService.updateCustomer(id, customer);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//    }
//}
