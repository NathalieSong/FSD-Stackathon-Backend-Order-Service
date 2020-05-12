package com.emart.order.service.orderservice.Entity;

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
    private Number quantity;

    @Column(name = "itemId")
    private String itemId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = false)
    private Order order;

    @Column(name = "total")
    private BigDecimal total;
}