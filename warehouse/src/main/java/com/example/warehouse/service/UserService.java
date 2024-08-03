package com.example.warehouse.service;

import com.example.warehouse.model.User;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.UserLoginDTO;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createManager(String username, String password, String adminUsername) {
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new SecurityException("Admin user not found"));
        if (hasRole(admin, "ROLE_ADMIN")) {
            User manager = new User();
            manager.setUsername(username);
            manager.setPassword(passwordEncoder.encode(password));
            Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                    .orElseThrow(() -> new RuntimeException("Manager role not found"));
            manager.setRoles(Set.of(managerRole));
            return userRepository.save(manager);
        }
        throw new SecurityException("Only admins can create managers.");
    }
    public boolean authenticateUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
    }

    public User registerCustomer(String username, String password) {
        User customer = new User();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User role not found"));
        customer.setRoles(Set.of(userRole));
        return userRepository.save(customer);
    }

    private boolean hasRole(User user, String roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getName().equals(roleName));
    }

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
