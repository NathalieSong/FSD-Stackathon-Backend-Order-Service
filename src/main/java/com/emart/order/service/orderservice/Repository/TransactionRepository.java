package com.emart.order.service.orderservice.repository;

import java.util.List;

import com.emart.order.service.orderservice.entity.Order;
import com.emart.order.service.orderservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByOrder(Order order);
}