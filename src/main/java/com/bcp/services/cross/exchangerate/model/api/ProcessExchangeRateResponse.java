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
            name = "operationDate",
            value = "Operation Date",
            example = "02/03/22",
            dataType = "string",
            position = 2
    )
    private String operationDate;

    @ApiModelProperty(
            name = "operationTime",
            value = "Operation Time",
            example = "20:44",
            dataType = "string",
            position = 3
    )
    private String operationTime;



}
