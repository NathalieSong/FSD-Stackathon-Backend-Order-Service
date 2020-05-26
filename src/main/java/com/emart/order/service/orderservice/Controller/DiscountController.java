package com.emart.order.service.orderservice.controller;

import com.emart.order.service.orderservice.service.DiscountService;
import com.emart.order.service.orderservice.dto.DiscountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/code")
    public DiscountDto getActiveByCode(@RequestParam String code) {
        return discountService.getActiveByCode(code);
    }

    @PostMapping("/add")
    public ResponseEntity<DiscountDto> addDiscount(@RequestBody DiscountDto dto) {
        return new ResponseEntity<DiscountDto>(
            discountService.addDiscount(dto),
            HttpStatus.CREATED
        );
    }
}