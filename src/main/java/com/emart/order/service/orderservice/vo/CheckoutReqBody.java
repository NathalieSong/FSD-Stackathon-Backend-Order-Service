package com.emart.order.service.orderservice.vo;

import java.util.List;
import lombok.Data;

@Data
public class CheckoutReqBody {
    private String buyerId;
    private List<CartItem> items;
    private DiscountReq discount;
    private String type;
}