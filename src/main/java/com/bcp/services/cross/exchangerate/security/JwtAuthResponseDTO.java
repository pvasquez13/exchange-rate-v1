package com.bcp.services.cross.exchangerate.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@ApiModel(description = "Response With the Bearer Token")
public class JwtAuthResponseDTO {

    @ApiModelProperty(
            name = "accessToken",
            value = "Access Token",
            example = "eyJhbGciOiJIUzUxMiJ9." +
                    "eyJzdWIiOiJwdmFzcXVlejFAZ21haWwuY29tIiwiaWF0IjoxNjQ2NjE3ODQ3LCJleHAiOjE2NDcyMjI2NDd9." +
                    "puPVByRJb3mUWoAeT9SjzizxMz33qWzasYVNyY8-R9lb7UGQeT5bdzgFGQB_wqSIn2-XTuYu2VXASgkaUKbERQ",
            dataType = "string"
    )
    private String accessToken;

    @ApiModelProperty(
            name = "tokenType",
            value = "Token Type",
            example = "Bearer",
            dataType = "string"
    )
    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

}
