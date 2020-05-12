package com.emart.order.service.orderservice.Repository;

import com.emart.order.service.orderservice.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    
}