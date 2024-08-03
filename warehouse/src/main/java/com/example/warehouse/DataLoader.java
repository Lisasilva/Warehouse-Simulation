
package com.example.warehouse;

//import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.warehouse.model.Role;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;

 @Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception { // remove "throws exception" and check
        // Adding roles
        addRoleIfNotPresent("ROLE_ADMIN");
        addRoleIfNotPresent("ROLE_MANAGER");
        addRoleIfNotPresent("ROLE_USER");

//        // Add a single predefined admin if not present
//        addAdminIfNotPresent("admin", "admin123");
//        //addUserIfNotPresent("admin", "admin123", "ROLE_ADMIN");
//
//    }
        // Adding users
        addUserIfNotPresent("admin", "admin123", "ROLE_ADMIN");
        addUserIfNotPresent("manager", "manager123", "ROLE_MANAGER");
        addUserIfNotPresent("user", "user123", "ROLE_USER");
    }
private void addRoleIfNotPresent(String roleName) {
    if (!roleRepository.existsByName(roleName)) {
//        Role role = new Role();
//        role.setName(roleName);
//        roleRepository.save(role);
          roleRepository.save(new Role(roleName));
    }
}

//     private void addAdminIfNotPresent(String username, String password) {
//         if (!userRepository.existsByUsername(username)) {
//             User admin = new User();
//             admin.setUsername(username);
//             admin.setPassword(passwordEncoder.encode(password));
//             Role adminRole = roleRepository.findByName("ROLE_ADMIN")
//                     .orElseThrow(() -> new RuntimeException("Admin role not found"));
//             admin.setRoles(Set.of(adminRole));
//             userRepository.save(admin);
//         }
//     }
// }

//          dk if i need this
    private void addUserIfNotPresent(String username, String password, String roleName) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Set.of(roleRepository.findByName(roleName).get()));
            userRepository.save(user);
        }
    }
}





//package com.example.warehouse;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.warehouse.model.Role;
//import com.example.warehouse.model.User;
//import com.example.warehouse.repository.RoleRepository;
//import com.example.warehouse.repository.UserRepository;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Roles
//        if (!roleRepository.existsByName("ROLE_ADMIN")) {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//        }
//        if (!roleRepository.existsByName("ROLE_MANAGER")) {
//            roleRepository.save(new Role("ROLE_MANAGER"));
//        }
//        if (!roleRepository.existsByName("ROLE_USER")) {
//            roleRepository.save(new Role("ROLE_USER"));
//        }
//
//        // Admin
//        if (!userRepository.existsByUsername("admin")) {
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("admin123"));
//            admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
//            userRepository.save(admin);
//        }
//
//        // Manager
//        if (!userRepository.existsByUsername("manager")) {
//            User manager = new User();
//            manager.setUsername("manager");
//            manager.setPassword(passwordEncoder.encode("manager123"));
//            manager.setRoles(Set.of(roleRepository.findByName("ROLE_MANAGER").get()));
//            userRepository.save(manager);
//        }
//
//        // Sample User
//        if (!userRepository.existsByUsername("user")) {
//            User user = new User();
//            user.setUsername("user");
//            user.setPassword(passwordEncoder.encode("user123"));
//            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").get()));
//            userRepository.save(user);
//        }
//    }
//}
//
//
//
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Add roles
//        addRoleIfNotPresent("ROLE_ADMIN");
//        addRoleIfNotPresent("ROLE_MANAGER");
//        addRoleIfNotPresent("ROLE_USER");
//
//        // Add users
//        addUserIfNotPresent("admin", "admin123", "ROLE_ADMIN");
//        addUserIfNotPresent("manager", "manager123", "ROLE_MANAGER");
//        addUserIfNotPresent("user", "user123", "ROLE_USER");
//    }
//
//    private void addRoleIfNotPresent(String roleName) {
//        if (!roleRepository.existsByName(roleName)) {
//            roleRepository.save(new Role(roleName));
//        }
//    }
//
//    private void addUserIfNotPresent(String username, String password, String roleName) {
//        if (!userRepository.existsByUsername(username)) {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(passwordEncoder.encode(password));
//            user.setRoles(Set.of(roleRepository.findByName(roleName).get()));
//            userRepository.save(user);
//        }
//    }
//}
