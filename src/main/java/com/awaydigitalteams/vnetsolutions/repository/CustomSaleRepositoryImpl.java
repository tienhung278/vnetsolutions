package com.awaydigitalteams.vnetsolutions.repository;

import com.awaydigitalteams.vnetsolutions.dto.SaleProductDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomSaleRepositoryImpl implements CustomSaleRepository {

    private final EntityManager entityManager;

    public CustomSaleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<SaleProductDTO> getUnitsRevenueByProduct() {
        List<SaleProductDTO> saleProductDTOS = new LinkedList<>();
        String sql = "SELECT productName, SUM(salesUnits) AS saleUnits, SUM(salesRevenue) AS saleRevenue " +
                "FROM Sale " +
                "GROUP BY productName " +
                "ORDER BY saleRevenue DESC";
        var results = entityManager.createQuery(sql, Object[].class).getResultList();
        for (Object[] result : results) {
            SaleProductDTO saleProductDTO = new SaleProductDTO((String) result[0], (Long) result[1], (BigDecimal) result[2]);
            saleProductDTOS.add(saleProductDTO);
        }
        return saleProductDTOS;
    }
}
