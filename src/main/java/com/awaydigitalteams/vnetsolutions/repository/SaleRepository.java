package com.awaydigitalteams.vnetsolutions.repository;

import com.awaydigitalteams.vnetsolutions.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>, CustomSaleRepository {

}
