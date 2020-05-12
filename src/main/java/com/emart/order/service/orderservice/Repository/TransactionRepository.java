package com.emart.order.service.orderservice.Repository;

import java.util.List;

import com.emart.order.service.orderservice.Entity.Order;
import com.emart.order.service.orderservice.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByOrder(Order order);
}