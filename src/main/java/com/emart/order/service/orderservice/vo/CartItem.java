package com.emart.order.service.orderservice.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class CartItem {
    private String id;
    private Number quantity;
    private String itemId;
    private String itemName;
    private String itemDesc;
    private BigDecimal itemPrice;
    private BigDecimal gst;
    private Number stockNumber;
    private Date createdDate;
    private String picture;
}