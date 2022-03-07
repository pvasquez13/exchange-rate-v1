package com.bcp.services.cross.exchangerate.business.impl;

import com.bcp.services.cross.exchangerate.business.ExchangeRateService;
import com.bcp.services.cross.exchangerate.business.processor.ExchangeRateProcessor;
import com.bcp.services.cross.exchangerate.model.api.*;
import com.bcp.services.cross.exchangerate.model.entity.ExchangeRate;
import com.bcp.services.cross.exchangerate.model.entity.ExchangeRateProcessed;
import com.bcp.services.cross.exchangerate.model.entity.ExchangeRateRequest;
import com.bcp.services.cross.exchangerate.repository.ExchangeRateProcessedRepository;
import com.bcp.services.cross.exchangerate.repository.ExchangeRateRepository;
import com.bcp.services.cross.exchangerate.repository.ExchangeRateRequestRepository;
import com.bcp.services.cross.exchangerate.utils.Util;
import com.bcp.services.cross.exchangerate.utils.constant.Constants;
import com.bcp.services.cross.exchangerate.utils.error.entity.EntityBadRequestException;
import com.bcp.services.cross.exchangerate.utils.error.entity.EntityNotFoundException;
import com.bcp.services.cross.exchangerate.utils.error.entity.EntityUnauthorizedException;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  @Autowired
  ExchangeRateRepository exchangeRateRepository;

  @Autowired
  ExchangeRateProcessor exchangeRateProcessor;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  Util util;

  @Autowired
  ExchangeRateRequestRepository exchangeRateRequestRepository;

  @Autowired
  ExchangeRateProcessedRepository exchangeRateProcessedRepository;

  @Override
  public Observable<ExchangeRateSearchListResponse> searchExchangeRateList() {
    return Observable.fromIterable(exchangeRateRepository.findAll().stream()
            .map(exchangeRateProcessor::processGetResponse)
            .collect(Collectors.toList()));
  }

  @Override
  public Single<ExchangeRateCreateResponse> createExchangeRate(ExchangeRateCreateRequest exchangeRateCreateRequest) {
    exchangeRateRepository.findByOriginCurrencyAndDestinationCurrency(
            exchangeRateCreateRequest.getOriginCurrency(),
            exchangeRateCreateRequest.getDestinationCurrency()).ifPresent(request -> {
              throw new EntityBadRequestException("Exchange rate already exists");
    });

    ExchangeRate exchangeRate = exchangeRateProcessor.process(exchangeRateCreateRequest);
    ExchangeRate transformersReturn = exchangeRateRepository.save(exchangeRate);
    return exchangeRateProcessor.processResponse(transformersReturn);
  }

  @Override
  public Completable updateExchangeRate(Long exchangeRateId, ExchangeRateCreateRequest request) {
    ExchangeRate exchangeRate = exchangeRateRepository.findById(exchangeRateId)
            .orElseThrow(() -> new EntityNotFoundException("the id sent does not exist"));
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    modelMapper.map(request, exchangeRate);
    exchangeRateRepository.saveAndFlush(exchangeRate);
    return Completable.complete();
  }

  @Override
  public Single<ExchangeRateCalculationResponse> calculateExchangeRate(ExchangeRateCalculationRequest
                                                                               exchangeRateCalculationRequest) {

    ExchangeRate exchangeRate;
    BigDecimal depositAmount;
    BigDecimal rate;
    if (exchangeRateCalculationRequest.getOriginCurrency().contentEquals(Constants.LOCAL_CURRENCY)) {
      exchangeRate = exchangeRateRepository.findByOriginCurrencyAndDestinationCurrency(
                      exchangeRateCalculationRequest.getOriginCurrency(),
                      exchangeRateCalculationRequest.getDestinationCurrency())
              .orElseThrow(() -> new EntityNotFoundException("Destination currency not supported, sorry :("));
      depositAmount = exchangeRateCalculationRequest.getAmount().divide(exchangeRate.getSellingRate(),
              2, RoundingMode.HALF_UP);
      rate = exchangeRate.getSellingRate();
    } else {
      exchangeRate = exchangeRateRepository.findByOriginCurrencyAndDestinationCurrency(
                      exchangeRateCalculationRequest.getDestinationCurrency(),
                      exchangeRateCalculationRequest.getOriginCurrency())
              .orElseThrow(() -> new EntityNotFoundException("Origin currency not supported, sorry :("));
      depositAmount = exchangeRateCalculationRequest.getAmount().multiply(exchangeRate.getPurchaseRate())
              .setScale(2, RoundingMode.HALF_UP);
      rate = exchangeRate.getPurchaseRate();
    }
    ExchangeRateCalculationResponse exchangeRateCalculationResponse  = exchangeRateProcessor
            .processCalculationRequestToResponse(exchangeRateCalculationRequest, exchangeRate, depositAmount, rate);
    exchangeRateRequestRepository.save(exchangeRateProcessor.processExchangeRateRequest(exchangeRateCalculationResponse));
    return Single.just(exchangeRateCalculationResponse);
  }

  @Override
  public Single<ProcessExchangeRateResponse> processExchangeRate(ProcessExchangeRateRequest processExchangeRateRequest) {
    ExchangeRateRequest exchangeRateRequest = exchangeRateRequestRepository
            .findByExchangeRateRequestId(processExchangeRateRequest.getExchangeRateId())
            .orElseThrow(() -> new EntityNotFoundException("Exchange rate ID does not exist"));
    LocalDateTime now = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE));
    if (exchangeRateRequest.getProcessed()) {
      return Single.error(new EntityBadRequestException("The operation has already been processed"));
    }
    if (now.isBefore(exchangeRateRequest.getDueDate())) {
      ExchangeRateProcessed exchangeRateProcessed = new ExchangeRateProcessed();
      LocalDateTime registerDateTime = LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE));
      exchangeRateProcessed.setRegisterDate(registerDateTime);
      ExchangeRateProcessed processed = exchangeRateProcessedRepository.save(exchangeRateProcessed);
      exchangeRateRequest.setProcessed(true);
      exchangeRateRequestRepository.saveAndFlush(exchangeRateRequest);
      ProcessExchangeRateResponse processExchangeRateResponse = new ProcessExchangeRateResponse();
      processExchangeRateResponse.setOperationNumber(processed.getOperationId().toString());
      String dateTimeFormatter = registerDateTime.format(DateTimeFormatter.ofPattern(Constants.PATTERN_DATE));
      processExchangeRateResponse.setOperationDateTime(dateTimeFormatter);
      return Single.just(processExchangeRateResponse);
    }
    return Single.error(new EntityUnauthorizedException("Session expired"));
  }
}
