package com.emart.order.service.orderservice.repository;

import java.util.Date;

import com.emart.order.service.orderservice.entity.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, String> {
    @Query("select d from Discount d where d.startDate <= :date and d.endDate >= :date and d.code = :code")
    Discount findActiveByCode(String code, Date date);
}