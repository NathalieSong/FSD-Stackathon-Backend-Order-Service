package com.emart.order.service.orderservice.repository;

import com.emart.order.service.orderservice.entity.EmartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<EmartOrder, String> {
    
}