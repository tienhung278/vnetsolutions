package com.awaydigitalteams.vnetsolutions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductDTO {

    String productName;
    Long saleUnits;
    BigDecimal saleRevenue;
}
