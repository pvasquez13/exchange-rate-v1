package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel(description = "Request to process an exchange rate")
public class ProcessExchangeRateRequest {

    @ApiModelProperty(
            name = "exchangeRateId",
            value = "Exchange Rate Id",
            example = "c2ec5e91-fb94-4aa5-bff2-30297c88fc5e",
            dataType = "string",
            required = true
    )
    @NotEmpty
    private String exchangeRateId;

    @ApiModelProperty(
            name = "chargeAccountId",
            value = "Charge Account Id",
            example = "4ce05c7c-3fcc-4fdd-8793-1a9942d4230d",
            dataType = "string",
            required = true
    )
    @NotEmpty
    private String chargeAccountId;

    @ApiModelProperty(
            name = "depositAccountId",
            value = "Deposit Account Id",
            example = "ccb51660-43bf-419e-8d2e-9effa6f09c93",
            dataType = "string",
            required = true
    )
    @NotEmpty
    private String depositAccountId;

}
