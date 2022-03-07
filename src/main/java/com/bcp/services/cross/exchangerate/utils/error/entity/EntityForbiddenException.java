package com.bcp.services.cross.exchangerate.utils.error.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Forbidden")
public class EntityForbiddenException extends RuntimeException {
  public EntityForbiddenException(String message) {
    super(message);
  }
}
