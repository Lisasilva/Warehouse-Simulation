package com.example.warehouse.controller;

import com.example.warehouse.model.OrderEntity;
import com.example.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return orderService.saveOrder(order);
    }

//    @PutMapping("/{id}")
//    public OrderEntity updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderEntity) {
//        orderEntity.setId(id);
//        return orderService.saveOrder(orderEntity);
//    }

    @PutMapping("/{id}")
    public OrderEntity updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderEntity) {
        return orderService.updateOrder(id,orderEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
