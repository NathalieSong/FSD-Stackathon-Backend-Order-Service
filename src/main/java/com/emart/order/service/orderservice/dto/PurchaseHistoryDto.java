package com.emart.order.service.orderservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PurchaseHistoryDto {
    private String id;
    private String itemId;
    private String itemName;
    private String itemDesc;
    private Number quantity;
    private String discountCode;
    private BigDecimal discountPercentage;
    private Date createdDate;
    private BigDecimal totalAfterDiscount;
    private String picture;
}