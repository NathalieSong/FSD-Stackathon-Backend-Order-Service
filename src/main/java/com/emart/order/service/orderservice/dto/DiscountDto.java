package com.emart.order.service.orderservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class DiscountDto {
    private String id;
    private String code;
    private BigDecimal percentage;
    private String description;
    private Date startDate;
    private Date endDate;
}