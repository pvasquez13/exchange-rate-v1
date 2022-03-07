package com.bcp.services.cross.exchangerate.utils.error.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "the id sent does not exist in the DB")
public class EntityNotFoundException extends RuntimeException{
  public EntityNotFoundException(String message) {
    super(message);
  }
}
