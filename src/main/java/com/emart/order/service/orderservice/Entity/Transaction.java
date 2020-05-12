package com.emart.order.service.orderservice.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.emart.order.service.orderservice.vo.TransactionType;

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GenericGenerator(name = "transaction-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "transaction-uuid2")
    private String id;

    @Column(name = "buyerId")
    private String buyerId;

    @Column(name = "type")
    private TransactionType type;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "total")
    private BigDecimal total;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = false)
    private Order order;
}