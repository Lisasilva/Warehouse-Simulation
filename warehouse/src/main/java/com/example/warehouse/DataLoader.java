package com.example.warehouse;

import com.example.warehouse.model.Customer;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.CustomerRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            // Ensure admin user is only created if not exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            // Load customers as users
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                if (!userRepository.existsByUsername(customer.getEmail())) {
                    User user = new User();
                    user.setUsername(customer.getEmail()); // or any unique username
                    user.setPassword(passwordEncoder.encode("defaultPassword")); // set a default password
                    user.setRole("USER");
                    user.setCustomer(customer);
                    userRepository.save(user);
                }
            }
        };
    }
}
