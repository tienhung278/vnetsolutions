package com.awaydigitalteams.vnetsolutions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    String salesDate;
    String storeName;
    String productName;
    Long salesUnits;
    BigDecimal salesRevenue;
}
