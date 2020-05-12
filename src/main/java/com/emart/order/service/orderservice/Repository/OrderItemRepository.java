package com.emart.order.service.orderservice.repository;

import com.emart.order.service.orderservice.entity.Order;
import java.util.List;

import com.emart.order.service.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    List<OrderItem> findByOrder(Order order);
}