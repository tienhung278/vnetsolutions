package com.awaydigitalteams.vnetsolutions.repository;

import com.awaydigitalteams.vnetsolutions.dto.SaleProductDTO;

import java.util.List;

public interface CustomSaleRepository {
    List<SaleProductDTO> getUnitsRevenueByProduct();
}
