package com.emart.order.service.orderservice.vo;

import java.util.List;
import com.emart.order.service.orderservice.dto.DiscountDto;
import lombok.Data;

@Data
public class CheckoutReqBody {
    private String buyerId;
    private List<CartItem> items;
    private DiscountDto discount;
    private String type;
}