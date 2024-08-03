package com.example.warehouse.controller;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.User;
import com.example.warehouse.model.UserLoginDTO;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        boolean isAuthenticated = userService.authenticateUser(userLoginDTO);
        if (isAuthenticated) {
            // Generate and return token or success message
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }



    // Get all products by location (for customers to view products in a specific location)
    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam String location) {
        return productService.getProductsByLocation(location);
    }

    // Place an order (customers can place orders)
    @PostMapping("/order")
    public String placeOrder(@RequestBody OrderEntity order) {
        productService.placeOrder(order);
        return "Order placed successfully";
    }
}
