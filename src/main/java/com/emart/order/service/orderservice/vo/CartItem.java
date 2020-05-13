package com.emart.order.service.orderservice.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class CartItem {
    private String id;
    private Integer quantity;
    private String itemId;
    private BigDecimal itemPrice;
    private BigDecimal gst;
}