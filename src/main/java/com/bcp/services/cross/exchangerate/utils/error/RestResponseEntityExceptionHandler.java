package com.bcp.services.cross.exchangerate.utils.error;

import com.bcp.services.cross.exchangerate.utils.constant.Constants;
import com.bcp.services.cross.exchangerate.utils.error.entity.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<CustomErrorResponse> handleClientValidation(EntityNotFoundException ex) {
    CustomErrorResponse errors = new CustomErrorResponse();
    errors.setTimestamp(LocalDateTime.now());
    errors.setError(ex.getMessage());
    errors.setStatus(HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({EntityBadRequestException.class})
  public ResponseEntity<CustomErrorResponse> handleClientValidation(EntityBadRequestException ex) {
    CustomErrorResponse errors = new CustomErrorResponse();
    errors.setTimestamp(LocalDateTime.now());
    errors.setError(ex.getMessage());
    errors.setStatus(HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({EntityUnauthorizedException.class})
  public ResponseEntity<CustomErrorResponse> handleClientValidation(EntityUnauthorizedException ex) {
    CustomErrorResponse errors = new CustomErrorResponse();
    errors.setTimestamp(LocalDateTime.now());
    errors.setError(ex.getMessage());
    errors.setStatus(HttpStatus.UNAUTHORIZED.value());

    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({EntityForbiddenException.class})
  public ResponseEntity<CustomErrorResponse> handleClientValidation(EntityForbiddenException ex) {
    CustomErrorResponse errors = new CustomErrorResponse();
    errors.setTimestamp(LocalDateTime.now());
    errors.setError(ex.getMessage());
    errors.setStatus(HttpStatus.FORBIDDEN.value());

    return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> errors = new ArrayList<String>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    ApiError apiError =
        new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(
        ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
        Constants.DESCRIPTION_MALFORMED_JSON_REQUEST));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

}
