package com.emart.order.service.orderservice.entity;

import java.math.BigDecimal;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GenericGenerator(name = "order-item-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "order-item-uuid2")
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "itemId")
    private String itemId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EmartOrder.class)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = false)
    private EmartOrder order;

    @Column(name = "total")
    private BigDecimal total;
}