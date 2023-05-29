package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.model.Sale;
import com.awaydigitalteams.vnetsolutions.model.SaleProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServicesImpl implements SaleServices {

    @Override
    public List<SaleProduct> getUnitsRevenueByProduct(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::getProductName))
                .values()
                .stream()
                .map(saleList -> saleList
                        .stream()
                        .reduce((s1, s2) -> new Sale(null,
                                null,
                                s1.getProductName(),
                                s1.getSalesUnits() + s2.getSalesUnits(),
                                s1.getSalesRevenue().add(s2.getSalesRevenue())))
                        .map(s1 -> new SaleProduct(s1.getProductName(), s1.getSalesUnits(), s1.getSalesRevenue()))
                        .get())
                .collect(Collectors.toList());
    }
}
