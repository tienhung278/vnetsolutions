package com.awaydigitalteams.vnetsolutions.api;

import com.awaydigitalteams.vnetsolutions.dto.SaleProductDTO;
import com.awaydigitalteams.vnetsolutions.service.SaleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sale")
public class SaleController {

    private final SaleServices saleServices;

    @Autowired
    public SaleController(SaleServices saleServices) {
        this.saleServices = saleServices;
    }

    @GetMapping("/unitsrevenuebyproduct")
    public List<SaleProductDTO> getUnitsRevenueByProduct() {
        return saleServices.getUnitsRevenueProduct();
    }
}
