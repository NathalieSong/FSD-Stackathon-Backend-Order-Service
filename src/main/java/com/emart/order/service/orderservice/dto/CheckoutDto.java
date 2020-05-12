package com.emart.order.service.orderservice.dto;

import lombok.Data;

@Data
public class CheckoutDto {
    private String orderId;

    public CheckoutDto(String orderId) {
        this.orderId = orderId;
    }
}