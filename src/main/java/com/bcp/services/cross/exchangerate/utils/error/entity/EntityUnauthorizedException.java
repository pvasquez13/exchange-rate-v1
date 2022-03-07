package com.bcp.services.cross.exchangerate.utils.error.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
public class EntityUnauthorizedException extends RuntimeException{
  public EntityUnauthorizedException(String message) {
    super(message);
  }
}
