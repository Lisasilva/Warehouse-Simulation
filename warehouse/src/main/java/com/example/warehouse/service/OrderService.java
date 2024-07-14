package com.example.warehouse.service;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity saveOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    // Corrected logic to eliminate the usage of setId()
    public OrderEntity updateOrder(Long id, OrderEntity orderEntity) {
        Optional<OrderEntity> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            OrderEntity updatedOrder = existingOrder.get();
            updatedOrder.setOrderDate(orderEntity.getOrderDate());
            updatedOrder.setStatus(orderEntity.getStatus());
            updatedOrder.setCustomer(orderEntity.getCustomer());
            updatedOrder.setProduct(orderEntity.getProduct());
            updatedOrder.setQuantity(orderEntity.getQuantity());
            updatedOrder.setPrice(orderEntity.getPrice());
            return orderRepository.save(updatedOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
