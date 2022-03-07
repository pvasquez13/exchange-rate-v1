package com.bcp.services.cross.exchangerate.exception;

import org.springframework.http.HttpStatus;

public class ExchangeRateAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public ExchangeRateAppException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
