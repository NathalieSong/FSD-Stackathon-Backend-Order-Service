package com.emart.order.service.orderservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "PurchaseHistory")
public class PurchaseHistory {
    @Id
    @GenericGenerator(name = "purchase-history-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "purchase-history-uuid2")
    private String id;

    @Column(name = "buyerId")
    private String buyerId;

    @Column(name = "sellerId")
    private String sellerId;

    @Column(name = "transactionId")
    private String transactionId;

    @Column(name = "itemId")
    private String itemId;

    @Column(name = "categoryId")
    private String categoryId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discountCode")
    private String discountCode;

    @Column(name = "discountPercentage")
    private BigDecimal discountPercentage;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "totalBeforeDiscount")
    private BigDecimal totalBeforeDiscount;
}