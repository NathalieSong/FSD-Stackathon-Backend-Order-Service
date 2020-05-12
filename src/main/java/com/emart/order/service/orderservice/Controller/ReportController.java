package com.emart.order.service.orderservice.controller;

import java.util.List;

import com.emart.order.service.orderservice.service.ReportService;
import com.emart.order.service.orderservice.dto.PurchaseHistoryDto;
import com.emart.order.service.orderservice.dto.SellingReportDto;
import com.emart.order.service.orderservice.dto.StockItemDto;
import com.emart.order.service.orderservice.vo.SellingReportFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/purchaseHistory/byBuyer")
    public List<PurchaseHistoryDto> getByBuyer(@RequestParam("buyerId") String buyerId) {
        return reportService.getByBuyer(buyerId);
    }

    @PostMapping("/selling/report")
    public List<SellingReportDto> getSellingReportByFilter(@RequestBody SellingReportFilter filter) {
        return reportService.getSellingReportByFilter(filter);
    }

    @GetMapping("/selling/stock")
    public List<StockItemDto> getAllSellingStock(@RequestParam("sellerId") String sellerId) {
        return reportService.getAllSellingStock(sellerId);
    }
}