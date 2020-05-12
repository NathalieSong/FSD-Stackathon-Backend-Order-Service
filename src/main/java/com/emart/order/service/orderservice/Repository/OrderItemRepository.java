package com.emart.order.service.orderservice.Repository;

import com.emart.order.service.orderservice.Entity.Order;
import java.util.List;

import com.emart.order.service.orderservice.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    List<OrderItem> findByOrder(Order order);
}