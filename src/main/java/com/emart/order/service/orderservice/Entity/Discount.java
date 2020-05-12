package com.emart.order.service.orderservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Discount")
public class Discount {
    @Id
    @GenericGenerator(name = "discount-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "discount-uuid2")
    private String id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "percentage")
    private BigDecimal percentage;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;
}