package com.example.warehouse.repository;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomer(User customer);
}
