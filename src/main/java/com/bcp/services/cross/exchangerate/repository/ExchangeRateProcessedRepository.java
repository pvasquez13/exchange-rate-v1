package com.bcp.services.cross.exchangerate.repository;

import com.bcp.services.cross.exchangerate.model.entity.ExchangeRateProcessed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateProcessedRepository extends JpaRepository<ExchangeRateProcessed, Long> {

}
