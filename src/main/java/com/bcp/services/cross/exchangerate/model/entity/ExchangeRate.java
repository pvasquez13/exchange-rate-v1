package com.bcp.services.cross.exchangerate.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long exchangeRateId;

  private String originCurrency;

  private String destinationCurrency;

  private BigDecimal sellingRate;

  private BigDecimal purchaseRate;

  private LocalDateTime creationTime;
}
