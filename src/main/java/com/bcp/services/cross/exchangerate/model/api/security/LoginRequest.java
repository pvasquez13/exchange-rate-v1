package com.bcp.services.cross.exchangerate.model.api.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel(description = "Request for Login")
public class LoginRequest {

  @ApiModelProperty(
          name = "usernameOrEmail",
          value = "Username Or Email",
          example = "pvasquez",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String usernameOrEmail;

  @ApiModelProperty(
          name = "password",
          value = "Password",
          example = "A#125*4rb0L",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String password;

}
