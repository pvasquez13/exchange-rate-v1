package com.bcp.services.cross.exchangerate.repository;

import com.bcp.services.cross.exchangerate.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  Optional<ExchangeRate> findByOriginCurrencyAndDestinationCurrency(String originCurrency, String destinationCurrency);

}
