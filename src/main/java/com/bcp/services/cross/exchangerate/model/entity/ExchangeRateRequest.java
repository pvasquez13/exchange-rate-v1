package com.bcp.services.cross.exchangerate.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "exchange_rate_requests")
public class ExchangeRateRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String exchangeRateRequestId;

  private BigDecimal chargeAmount;

  private String chargeCurrency;

  private BigDecimal depositAmount;

  private String depositCurrency;

  private BigDecimal rate;

  private BigDecimal ratePurchase;

  private BigDecimal rateSale;

  private LocalDateTime registerDate;

  private LocalDateTime dueDate;

  private Boolean processed;
}
