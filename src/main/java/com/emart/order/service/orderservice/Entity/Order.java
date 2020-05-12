package com.emart.order.service.orderservice.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GenericGenerator(name = "order-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "order-uuid2")
    private String id;

    @Column(name = "buyerId")
    private String buyerId;

    @Column(name = "discountCode")
    private String discountCode;

    @Column(name = "discountPercentage")
    private BigDecimal discountPercentage;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "tax")
    private BigDecimal tax;
}