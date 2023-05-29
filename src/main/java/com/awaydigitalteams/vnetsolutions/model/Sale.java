package com.awaydigitalteams.vnetsolutions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Sale {
    LocalDate salesDate;
    String storeName;
    String productName;
    Long salesUnits;
    BigDecimal salesRevenue;
}
