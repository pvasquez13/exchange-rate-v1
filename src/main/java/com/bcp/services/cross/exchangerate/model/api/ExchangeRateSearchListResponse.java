package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(description = "Response model for exchange rate list")
public class ExchangeRateSearchListResponse {

    @ApiModelProperty(
            name = "exchangeRateId",
            value = "Exchange rate id",
            example = "1",
            dataType = "long",
            position = 1
    )
    private Long exchangeRateId;

    @ApiModelProperty(
            name = "originCurrency",
            value = "Origin currency",
            example = "PEN",
            dataType = "string",
            position = 2
    )
    private String originCurrency;

    @ApiModelProperty(
            name = "destinationCurrency",
            value = "Destination currency",
            example = "USD",
            dataType = "string",
            position = 3
    )
    private String destinationCurrency;

    @ApiModelProperty(
            name = "sellingRate",
            value = "Selling rate",
            example = "3.81",
            dataType = "bigDecimal",
            position = 4
    )
    private BigDecimal sellingRate;

    @ApiModelProperty(
            name = "purchaseRate",
            value = "Purchase rate",
            example = "3.76",
            dataType = "bigDecimal",
            position = 5
    )
    private BigDecimal purchaseRate;

}
