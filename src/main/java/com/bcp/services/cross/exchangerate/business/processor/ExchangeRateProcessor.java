package com.bcp.services.cross.exchangerate.business.processor;

import com.bcp.services.cross.exchangerate.model.api.*;
import com.bcp.services.cross.exchangerate.model.entity.ExchangeRate;
import com.bcp.services.cross.exchangerate.model.entity.ExchangeRateRequest;
import com.bcp.services.cross.exchangerate.utils.Util;
import com.bcp.services.cross.exchangerate.utils.constant.Constants;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class ExchangeRateProcessor {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  Util util;

  public ExchangeRateSearchListResponse processGetResponse(ExchangeRate exchangeRate) {
    ExchangeRateSearchListResponse exchangeRateSearchListResponse = new ExchangeRateSearchListResponse();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    modelMapper.map(exchangeRate, exchangeRateSearchListResponse);
    return exchangeRateSearchListResponse;
  }

  public ExchangeRate process(ExchangeRateCreateRequest exchangeRateCreateRequest) {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setOriginCurrency(exchangeRateCreateRequest.getOriginCurrency());
    exchangeRate.setDestinationCurrency(exchangeRateCreateRequest.getDestinationCurrency());
    exchangeRate.setSellingRate(exchangeRateCreateRequest.getSellingRate());
    exchangeRate.setPurchaseRate(exchangeRateCreateRequest.getPurchaseRate());
    exchangeRate.setCreationTime(LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE)));
    return exchangeRate;
  }

  public Single<ExchangeRateCreateResponse> processResponse(ExchangeRate exchangeRate){
    ExchangeRateCreateResponse exchangeRateCreateResponse = new ExchangeRateCreateResponse();
    exchangeRateCreateResponse.setExchangeRateId(exchangeRate.getExchangeRateId());
    return Single.just(exchangeRateCreateResponse);
  }

  public ExchangeRateCalculationResponse processCalculationRequestToResponse(ExchangeRateCalculationRequest exchangeRateCalculationRequest,
                                                                             ExchangeRate exchangeRate,
                                                                             BigDecimal depositAmount,
                                                                             BigDecimal rate) {
    ExchangeRateCalculationResponse exchangeRateCalculationResponse = new ExchangeRateCalculationResponse();
    exchangeRateCalculationResponse.setExchangeRateId(util.generateRandomAlphanumericString());
    exchangeRateCalculationResponse.setChargeAmount(exchangeRateCalculationRequest.getAmount());
    exchangeRateCalculationResponse.setChargeCurrency(exchangeRateCalculationRequest.getOriginCurrency());
    exchangeRateCalculationResponse.setDepositCurrency(exchangeRateCalculationRequest.getDestinationCurrency());
    exchangeRateCalculationResponse.setRatePurchase(exchangeRate.getPurchaseRate());
    exchangeRateCalculationResponse.setRateSale(exchangeRate.getSellingRate());
    exchangeRateCalculationResponse.setDepositAmount(depositAmount);
    exchangeRateCalculationResponse.setRate(rate);
    LocalDateTime today = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE));
    exchangeRateCalculationResponse.setRegisterDate(util.currentDateTime(today));
    exchangeRateCalculationResponse.setDueDate(util.getDueDateTime(today));
    return  exchangeRateCalculationResponse;
  }

  public ExchangeRateRequest processExchangeRateRequest(
          ExchangeRateCalculationResponse exchangeRateCalculationResponse) {
    ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();
    exchangeRateRequest.setExchangeRateRequestId(exchangeRateCalculationResponse.getExchangeRateId());
    exchangeRateRequest.setChargeAmount(exchangeRateCalculationResponse.getChargeAmount());
    exchangeRateRequest.setChargeCurrency(exchangeRateCalculationResponse.getChargeCurrency());
    exchangeRateRequest.setDepositAmount(exchangeRateCalculationResponse.getDepositAmount());
    exchangeRateRequest.setDepositCurrency(exchangeRateCalculationResponse.getDepositCurrency());
    exchangeRateRequest.setRate(exchangeRateCalculationResponse.getRate());
    exchangeRateRequest.setRatePurchase(exchangeRateCalculationResponse.getRatePurchase());
    exchangeRateRequest.setRateSale(exchangeRateCalculationResponse.getRateSale());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.PATTERN_DATE);
    LocalDateTime dateTime = LocalDateTime.parse(exchangeRateCalculationResponse.getRegisterDate(), formatter);
    exchangeRateRequest.setRegisterDate(dateTime);
    LocalDateTime dueTime = LocalDateTime.parse(exchangeRateCalculationResponse.getDueDate(), formatter);
    exchangeRateRequest.setDueDate(dueTime);
    exchangeRateRequest.setProcessed(false);

    return exchangeRateRequest;

  }
}
