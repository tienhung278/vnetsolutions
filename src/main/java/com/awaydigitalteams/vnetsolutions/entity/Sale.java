package com.awaydigitalteams.vnetsolutions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@Table(name = "sale")
@NoArgsConstructor
public class Sale {

    @Column(name = "sales_date")
    LocalDate salesDate;
    @Column(name = "store_name")
    String storeName;
    @Column(name = "product_name")
    String productName;
    @Column(name = "sales_units")
    Long salesUnits;
    @Column(name = "sales_revenue")
    BigDecimal salesRevenue;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Sale(LocalDate salesDate, String storeName, String productName, Long salesUnits, BigDecimal salesRevenue) {
        this.salesDate = salesDate;
        this.storeName = storeName;
        this.productName = productName;
        this.salesUnits = salesUnits;
        this.salesRevenue = salesRevenue;
    }
}
