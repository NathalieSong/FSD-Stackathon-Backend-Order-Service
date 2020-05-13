package com.emart.order.service.orderservice.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DiscountReq {
    private String code;
    private BigDecimal percentage;
}