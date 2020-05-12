package com.emart.order.service.orderservice.FeignClient;

import com.emart.order.service.orderservice.vo.Item;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://127.0.0.1:9001")
@RequestMapping("api")
public interface ItemServiceClient {
    @GetMapping("")
    public Item getById(@RequestParam("itemId") String itemId);
}