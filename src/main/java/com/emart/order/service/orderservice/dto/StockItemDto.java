package com.emart.order.service.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockItemDto {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer noInStock;
    private Integer noSold;
    private String picture;
}