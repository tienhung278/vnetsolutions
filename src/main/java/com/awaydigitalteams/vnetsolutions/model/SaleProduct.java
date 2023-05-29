package com.awaydigitalteams.vnetsolutions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SaleProduct {

    String productName;
    Long saleUnits;
    BigDecimal saleRevenue;
}
