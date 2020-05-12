package com.emart.order.service.orderservice.controller;

import com.emart.order.service.orderservice.service.DiscountService;
import com.emart.order.service.orderservice.dto.DiscountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("")
    public DiscountDto getActiveByCode(@RequestParam String code) {
        return discountService.getActiveByCode(code);
    }
}