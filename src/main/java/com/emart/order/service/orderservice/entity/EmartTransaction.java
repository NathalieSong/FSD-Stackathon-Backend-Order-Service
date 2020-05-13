package com.emart.order.service.orderservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.emart.order.service.orderservice.vo.TransactionType;

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "EmartTransaction")
public class EmartTransaction {
    @Id
    @GenericGenerator(name = "transaction-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "transaction-uuid2")
    private String id;

    @Column(name = "buyerId")
    private String buyerId;

    @Column(name = "type")
    private String type;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "total")
    private BigDecimal total;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = EmartOrder.class)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = false)
    private EmartOrder order;
}