package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.model.Sale;
import com.awaydigitalteams.vnetsolutions.model.SaleProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class SaleServicesImplTest {


    SaleServices saleServices;

    @BeforeEach
    void setUp() {
        saleServices = new SaleServicesImpl();
    }

    @Test
    void getUnitsRevenueByProduct() {
        //Arrange
        Sale sale = new Sale(LocalDate.now(), "store1", "product1", 1L, BigDecimal.TEN);
        Sale sale2 = new Sale(LocalDate.now(), "store2", "product1", 1L, BigDecimal.TEN);
        Sale sale3 = new Sale(LocalDate.now(), "store3", "product2", 2L, BigDecimal.TEN);
        Sale sale4 = new Sale(LocalDate.now(), "store3", "product2", 2L, BigDecimal.TEN);
        Sale sale5 = new Sale(LocalDate.now(), "store3", "product3", 3L, BigDecimal.TEN);
        List<Sale> sales = List.of(sale, sale2, sale3, sale4, sale5);

        //Act
        var result = saleServices.getUnitsRevenueByProduct(sales);
        var product1 = result.stream().filter(s -> s.getProductName().contentEquals("product1")).findFirst().get();

        //Assert
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(2, product1.getSaleUnits());
        Assertions.assertEquals(BigDecimal.valueOf(20L), product1.getSaleRevenue());
        Assertions.assertInstanceOf(SaleProduct.class, product1);
    }
}