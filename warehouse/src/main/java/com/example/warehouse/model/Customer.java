package com.example.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    //this is for security, each customer needs to have their own login credentials
    @OneToOne(mappedBy = "customer")
    private User user;
}

// used the @Getter @Setter annotations despite the presence of @Data cause
// i have used getters and setters in the service classes of each entity respectively
// hence @Getter @Setter need to be specified explicitly for the compiler to fetch these methods