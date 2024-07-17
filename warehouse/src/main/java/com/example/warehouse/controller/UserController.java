package com.example.warehouse.controller;

import com.example.warehouse.model.User;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}



//package com.example.warehouse.controller;
//
//import com.example.warehouse.model.Role;
//import com.example.warehouse.model.User;
//import com.example.warehouse.repository.RoleRepository;
//import com.example.warehouse.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public User registerUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
//        return userRepository.save(user);
//    }
//}
