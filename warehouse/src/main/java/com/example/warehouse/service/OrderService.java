package com.example.warehouse.service;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java. util. stream. Collectors;
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

    public OrderEntity markOrderAsComplete(Long id) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            OrderEntity order = orderOptional.get();
            order.setStatus("Complete");
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }


    // OrderService.java
    public List<OrderEntity> getOrdersByWarehouse(Long warehouseId) {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .filter(order -> order.getProduct().getWarehouse().getId().equals(warehouseId))
                .collect(Collectors.toList());
    }


}
