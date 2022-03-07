package com.bcp.services.cross.exchangerate.model.api.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel(description = "Request for registration")
public class UserRequest {

  @ApiModelProperty(
          name = "name",
          value = "Name",
          example = "Peter",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String name;

  @ApiModelProperty(
          name = "username",
          value = "Username",
          example = "pvasquez",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String username;

  @ApiModelProperty(
          name = "email",
          value = "Email",
          example = "pvasquez@gmail.com",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String email;

  @ApiModelProperty(
          name = "password",
          value = "Password",
          example = "1#65x4a58s",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String password;

  @ApiModelProperty(
          name = "role",
          value = "Role",
          example = "ADMIN",
          dataType = "string",
          required = true
  )
  @NotEmpty
  private String role;
}
