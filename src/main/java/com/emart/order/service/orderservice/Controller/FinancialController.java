package com.emart.order.service.orderservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emart.order.service.orderservice.service.FinancialService;
import com.emart.order.service.orderservice.dto.CheckoutDto;
import com.emart.order.service.orderservice.dto.TaxDto;
import com.emart.order.service.orderservice.vo.CheckoutReqBody;
import com.emart.order.service.orderservice.vo.TaxReqBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/financial")
public class FinancialController {
    @Autowired
    private FinancialService financialService;

    @PostMapping("/getTax")
    public TaxDto getTax(@RequestBody TaxReqBody taxReq) {        
        return financialService.getTax(taxReq);
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutDto> checkout(@RequestBody CheckoutReqBody checkoutReq) {
        return new ResponseEntity<CheckoutDto>(
            financialService.checkout(checkoutReq),
            HttpStatus.OK
        );
    }
    
}