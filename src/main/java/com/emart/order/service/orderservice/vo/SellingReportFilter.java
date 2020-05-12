package com.emart.order.service.orderservice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SellingReportFilter {
    private Date startPeriod;
    private Date endPeriod;
    private String sellerId;
}