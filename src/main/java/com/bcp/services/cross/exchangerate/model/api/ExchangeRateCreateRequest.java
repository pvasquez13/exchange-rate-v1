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
@ApiModel(description = "Request for exchange rate creation")
public class ExchangeRateCreateRequest {

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
            name = "sellingRate",
            value = "Selling rate",
            example = "3.789000",
            dataType = "bigDecimal",
            required = true
    )
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 6, message = "numeric value out of bounds (<6 digits>.<5 digits> expected)")
    private BigDecimal sellingRate;

    @ApiModelProperty(
            name = "purchaseRate",
            value = "Purchase rate",
            example = "3.717000",
            dataType = "bigDecimal",
            required = true
    )
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 6, message = "numeric value out of bounds (<6 digits>.<5 digits> expected)")
    private BigDecimal purchaseRate;

}
