package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Response model for exchange rate creation")
public class ExchangeRateCreateResponse {

    @ApiModelProperty(
            name = "exchangeRateId",
            value = "Exchange rate ID",
            example = "1",
            dataType = "long"
    )
    private Long exchangeRateId;

}
