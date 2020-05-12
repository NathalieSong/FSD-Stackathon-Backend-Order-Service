package com.emart.order.service.orderservice.Repository;

import java.util.List;

import com.emart.order.service.orderservice.Entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
    List<PurchaseHistory> findByBuyerId(String buyerId);
}