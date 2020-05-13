package com.emart.order.service.orderservice.repository;

import java.util.List;

import com.emart.order.service.orderservice.entity.EmartOrder;
import com.emart.order.service.orderservice.entity.EmartTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<EmartTransaction, String> {
    List<EmartTransaction> findByOrder(EmartOrder order);
}