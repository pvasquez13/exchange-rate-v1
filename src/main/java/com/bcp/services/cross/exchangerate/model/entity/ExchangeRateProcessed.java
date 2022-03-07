package com.bcp.services.cross.exchangerate.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate_processed")
public class ExchangeRateProcessed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long operationId;
  private LocalDateTime registerDate;
}
