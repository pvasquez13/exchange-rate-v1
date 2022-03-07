package com.bcp.services.cross.expose.web;

import com.bcp.services.cross.exchangerate.business.ExchangeRateService;
import com.bcp.services.cross.exchangerate.model.api.*;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Main controller that exposes the service via HTTP / Rest for
 * ExchangeRateController resource operations<br/>
 * <b>Class<b/>: ExchangeRateController<br/>
 * <b>Copyright<b/>: &copy; 2022 Peter Vasquez Sotelo<br/>
 *
 * @author Peter Vasquez Sotelo <br/>
 * <u>Developed by</u>:<br/>
 * <ul>
 * <li>Peter Vasquez Sotelo</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Feb 03, 2022 Creaci&oacute;n de la clase./li>
 * </ul>
 * @version 1.0
 */
@RestController
@Api(tags = "ExchangeRate")
@RequestMapping("/api/bcp/v1/exchange-rate")
@Slf4j
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;
    /**
     * Endpoint showing list of exchange rate
     *
     * @return {@link ExchangeRateSearchListResponse} Exchange rate list
     */
    @ApiOperation(
            value = "returns the list of exchange rates",
            httpMethod = "GET"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Returned exchange rate list successfully.",
                    response = ExchangeRateSearchListResponse.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Error getting data."
            ),
            @ApiResponse(
                    code = 503,
                    message = "The service is not available."
            )
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Observable<ExchangeRateSearchListResponse> searchExchangeRateList() {
        return exchangeRateService.searchExchangeRateList();
    }

    /**
     * Endpoint that allows creating an exchange rate
     *
     * @param exchangeRateCreateRequest parameter with the data of the exchange rate to create
     * @return {@link ExchangeRateCreateResponse} Exchange rate ID
     */
    @ApiOperation(
            value = "Allows to create a new exchange rate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "the exchange rate was registered successfully.",
                    response = ExchangeRateCreateResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "the data sent is incorrect."
            )
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Single<ExchangeRateCreateResponse> createExchangeRate(
            @Valid @RequestBody ExchangeRateCreateRequest exchangeRateCreateRequest) {
        return exchangeRateService.createExchangeRate(exchangeRateCreateRequest);
    }

    /**
     * Endpoint that updates an exchange rate
     *
     * @param exchangeRateCreateRequest parameter with the data of the exchange rate to update
     */
    @ApiOperation(
            value = "Allows updating the data of an existing exchange rate by means of its id",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "PATCH"
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "The exchange rate was updated successfully."),
            @ApiResponse(code = 400, message = "The data sent is incorrect."),
            @ApiResponse(code = 500, message = "Error trying to update resource.")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{exchangeRateId}")
    public Completable updateExchangeRate(@PathVariable("exchangeRateId") Long exchangeRateId,
                                          @Valid @RequestBody ExchangeRateCreateRequest exchangeRateCreateRequest) {
        return exchangeRateService.updateExchangeRate(exchangeRateId, exchangeRateCreateRequest);
    }

    /**
     * Endpoint that calculates exchange rate
     *
     * @param exchangeRateCalculationRequest parameter with the data for calculate exchange rate
     * @return {@link ExchangeRateCalculationResponse} exchange rate information
     */
    @ApiOperation(
            value = "Calculate exchange rate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "the exchange rate was calculated successfully.",
                    response = ExchangeRateCalculationResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "the data sent is incorrect."
            ),
            @ApiResponse(
                    code = 500,
                    message = "Error trying to calculate exchange rate."
            )
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/calculate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Single<ExchangeRateCalculationResponse> calculateExchangeRate(
            @Valid @RequestBody ExchangeRateCalculationRequest exchangeRateCalculationRequest) {
        return exchangeRateService.calculateExchangeRate(exchangeRateCalculationRequest);
    }

    /**
     * Endpoint that process exchange rate
     *
     * @param processExchangeRateRequest parameter with the data for processing exchange rate
     * @return {@link ExchangeRateCalculationResponse} information of the operation carried out
     */
    @ApiOperation(
            value = "Process exchange rate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "the exchange rate was processed successfully.",
                    response = ProcessExchangeRateResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "the data sent is incorrect."
            ),
            @ApiResponse(
                    code = 500,
                    message = "Error trying to process exchange rate."
            )
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/exchange-order", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Single<ProcessExchangeRateResponse> processExchangeRate(
            @Valid @RequestBody ProcessExchangeRateRequest processExchangeRateRequest) {
        return exchangeRateService.processExchangeRate(processExchangeRateRequest);
    }
}
