package com.bcp.services.cross.exchangerate.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(description = "Request for exchange rate calculation")
public class ExchangeRateCalculationRequest {

    @ApiModelProperty(
            name = "originCurrency",
            value = "Origin currency",
            example = "PEN",
            dataType = "string",
            required = true
    )
    @NotEmpty
    @Size(min = 3, max = 4, message = "originCurrency must be between 3 and 4 characters")
    private String originCurrency;

    @ApiModelProperty(
            name = "destinationCurrency",
            value = "Destination currency",
            example = "USD",
            dataType = "string",
            required = true
    )
    @NotEmpty
    @Size(min = 3, max = 4, message = "name must be between 3 and 4 characters")
    private String destinationCurrency;

    @ApiModelProperty(
            name = "amount",
            value = "Amount",
            example = "1000.00",
            dataType = "bigDecimal",
            required = true
    )
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2, message = "numeric value out of bounds (<6 digits>.<2 digits> expected)")
    private BigDecimal amount;

}
