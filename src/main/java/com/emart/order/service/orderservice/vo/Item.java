package com.emart.order.service.orderservice.vo;

import java.math.BigDecimal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String subCategoryId;
    private BigDecimal price;
    private String manufacturer;
    private String remarks;
    private Integer stockNumber;
    private String sellerId;
    private JSONObject specification;
    private JSONArray pictures;
}