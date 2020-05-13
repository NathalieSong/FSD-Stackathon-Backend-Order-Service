package com.emart.order.service.orderservice.FeignClient;

import com.emart.order.service.orderservice.vo.Item;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.items.name}", url = "${feign.items.url}")
@RequestMapping("api/item")
public interface ItemServiceClient {
    @GetMapping("/byId")
    public Item getById(@RequestParam("itemId") String itemId);
}