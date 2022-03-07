package com.bcp.services.cross.exchangerate.repository;

import com.bcp.services.cross.exchangerate.model.entity.ExchangeRateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRequestRepository extends JpaRepository<ExchangeRateRequest, Long> {

  Optional<ExchangeRateRequest> findByExchangeRateRequestId(String exchangeRateRequestId);

}
