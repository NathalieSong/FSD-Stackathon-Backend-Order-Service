package com.emart.order.service.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TaxDto {
    private BigDecimal tax;

    public TaxDto(BigDecimal tax) {
        this.tax = tax;
    }
}