package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.dto.SaleDTO;
import com.awaydigitalteams.vnetsolutions.dto.SaleProductDTO;

import java.util.List;

public interface SaleServices {
    void save(List<SaleDTO> saleDTOS);

    List<SaleProductDTO> getUnitsRevenueProduct();
}
