package com.jmb.batchapp.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "SALES")
public class Sale {

    public Sale() {}

    @Id @GeneratedValue
    private Integer id;
    private String sellerId;
    private Date salesDate;
    private String product;
    private Integer quantity;
}
