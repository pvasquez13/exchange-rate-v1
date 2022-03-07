package com.bcp.services.cross.exchangerate.business;

import com.bcp.services.cross.exchangerate.model.api.*;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ExchangeRateService {

  Observable<ExchangeRateSearchListResponse> searchExchangeRateList();

  Single<ExchangeRateCreateResponse> createExchangeRate(ExchangeRateCreateRequest exchangeRateCreateRequest);

  Completable updateExchangeRate(Long exchangeRateId, ExchangeRateCreateRequest request);

  Single<ExchangeRateCalculationResponse> calculateExchangeRate(ExchangeRateCalculationRequest
                                                                        exchangeRateCalculationRequest);

  Single<ProcessExchangeRateResponse> processExchangeRate(ProcessExchangeRateRequest processExchangeRateRequest);

}
