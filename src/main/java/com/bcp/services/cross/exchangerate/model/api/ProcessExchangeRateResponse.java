package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Exchange rate process response model")
public class ProcessExchangeRateResponse {

    @ApiModelProperty(
            name = "operationNumber",
            value = "Operation Number",
            example = "00000856",
            dataType = "string",
            position = 1
    )
    private String operationNumber;

    @ApiModelProperty(
            name = "operationDateTime",
            value = "Operation DateTime",
            example = "2022-03-01 20:51:39",
            dataType = "string",
            position = 2
    )
    private String operationDateTime;


}
