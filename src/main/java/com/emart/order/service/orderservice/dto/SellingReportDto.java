package com.emart.order.service.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SellingReportDto {
    private String itemId;
    private String itemName;
    private Integer noSold;
    private BigDecimal total;
}