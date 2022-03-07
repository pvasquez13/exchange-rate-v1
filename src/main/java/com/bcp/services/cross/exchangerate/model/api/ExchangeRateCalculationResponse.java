package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(description = "Response model for exchange rate service")
public class ExchangeRateCalculationResponse {

    @ApiModelProperty(
            name = "exchangeRateId",
            value = "Exchange Rate ID",
            example = "b5100a6c-57c9-44c1-975c-b782b4e51b77",
            dataType = "String",
            position = 1
    )
    private String exchangeRateId;

    @ApiModelProperty(
            name = "chargeAmount",
            value = "Charge amount",
            example = "2197.6200",
            dataType = "BigDecimal",
            position = 2
    )
    private BigDecimal chargeAmount;

    @ApiModelProperty(
            name = "chargeCurrency",
            value = "Charge currency",
            example = "PEN",
            dataType = "string",
            position = 3
    )
    private String chargeCurrency;

    @ApiModelProperty(
            name = "depositAmount",
            value = "Deposit amount",
            example = "580.00",
            dataType = "bigDecimal",
            position = 4
    )
    private BigDecimal depositAmount;

    @ApiModelProperty(
            name = "depositCurrency",
            value = "Deposit currency",
            example = "USD",
            dataType = "string",
            position = 5
    )
    private String depositCurrency;

    @ApiModelProperty(
            name = "rate",
            value = "Rate",
            example = "3.78",
            dataType = "bigDecimal",
            position = 6
    )
    private BigDecimal rate;

    @ApiModelProperty(
            name = "ratePurchase",
            value = "Rate purchase",
            example = "3.71",
            dataType = "bigDecimal",
            position = 7
    )
    private BigDecimal ratePurchase;

    @ApiModelProperty(
            name = "rateSale",
            value = "Rate sale",
            example = "3.78",
            dataType = "bigDecimal",
            position = 8
    )
    private BigDecimal rateSale;

    @ApiModelProperty(
            name = "registerDate",
            value = "Register Date",
            example = "2022-03-01T20:46:39",
            dataType = "string",
            position = 9
    )
    private String registerDate;

    @ApiModelProperty(
            name = "dueDate",
            value = "Due Date",
            example = "2022-03-01T20:51:39",
            dataType = "string",
            position = 9
    )
    private String dueDate;

}
