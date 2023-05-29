package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.dto.SaleDTO;
import com.awaydigitalteams.vnetsolutions.dto.SaleProductDTO;
import com.awaydigitalteams.vnetsolutions.entity.Sale;
import com.awaydigitalteams.vnetsolutions.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SaleServicesImpl implements SaleServices {

    private final SaleRepository saleRepository;

    public SaleServicesImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void save(List<SaleDTO> saleDTOS) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<Sale> sales = saleDTOS
                .stream()
                .map(s -> new Sale(LocalDate.parse(s.getSalesDate(), formatter),
                        s.getStoreName(),
                        s.getProductName(),
                        s.getSalesUnits(),
                        s.getSalesRevenue()))
                .toList();
        saleRepository.saveAll(sales);
    }

    @Override
    public List<SaleProductDTO> getUnitsRevenueProduct() {
        return saleRepository.getUnitsRevenueByProduct();
    }
}
