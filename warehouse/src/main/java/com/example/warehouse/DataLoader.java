package com.example.warehouse;

import com.example.warehouse.model.Customer;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.CustomerRepository;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            // Ensure admin role exists
            Role adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);

            // Ensure manager role exists
            Role managerRole = new Role("ROLE_MANAGER");
            roleRepository.save(managerRole);

            // Ensure user role exists
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);

            // Ensure admin user is only created if not exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                admin.setRoles(roles);
                userRepository.save(admin);
            }

            // Load customers as users
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                if (!userRepository.existsByUsername(customer.getEmail())) {
                    User user = new User();
                    user.setUsername(customer.getEmail()); // or any unique username
                    user.setPassword(passwordEncoder.encode("defaultPassword")); // set a default password
                    Set<Role> roles = new HashSet<>();
                    roles.add(userRole);
                    user.setRoles(roles);
                    user.setCustomer(customer);
                    userRepository.save(user);
                }
            }
        };
    }
}

//package com.example.warehouse;
//
//import com.example.warehouse.model.Customer;
//import com.example.warehouse.model.Role;
//import com.example.warehouse.model.User;
//import com.example.warehouse.repository.CustomerRepository;
//import com.example.warehouse.repository.RoleRepository;
//import com.example.warehouse.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class DataLoader {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner loadData() {
//        return (args) -> {
//            // Ensure admin user is only created if not exists
//            if (!userRepository.existsByUsername("admin")) {
//                User admin = new User();
//                admin.setUsername("admin");
//                admin.setPassword(passwordEncoder.encode("admin123"));
//
//                Role adminRole = new Role("ROLE_ADMIN");
//                roleRepository.save(adminRole);
//
//                Set<Role> roles = new HashSet<>();
//                roles.add(adminRole);
//
//                admin.setRoles(roles);
//                userRepository.save(admin);
//            }
//
//            // Ensure manager role exists
//            Role managerRole = new Role("ROLE_MANAGER");
//            roleRepository.save(managerRole);
//
//            // Load customers as users
//            List<Customer> customers = customerRepository.findAll();
//            for (Customer customer : customers) {
//                if (!userRepository.existsByUsername(customer.getEmail())) {
//                    User user = new User();
//                    user.setUsername(customer.getEmail()); // or any unique username
//                    user.setPassword(passwordEncoder.encode("defaultPassword")); // set a default password
//
//                    Role userRole = new Role("ROLE_USER");
//                    roleRepository.save(userRole);
//
//                    Set<Role> roles = new HashSet<>();
//                    roles.add(userRole);
//
//                    user.setRoles(roles);
//                    user.setCustomer(customer);
//                    userRepository.save(user);
//                }
//            }
//        };
//    }
//}
