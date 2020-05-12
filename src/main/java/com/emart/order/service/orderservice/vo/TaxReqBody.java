package com.emart.order.service.orderservice.vo;

import java.util.List;
import lombok.Data;

@Data
public class TaxReqBody {
    private List<CartItem> items;
}