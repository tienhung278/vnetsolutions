package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.model.Sale;
import com.awaydigitalteams.vnetsolutions.model.SaleProduct;

import java.util.List;

public interface SaleServices {
    List<SaleProduct> getUnitsRevenueByProduct(List<Sale> sales);
}
