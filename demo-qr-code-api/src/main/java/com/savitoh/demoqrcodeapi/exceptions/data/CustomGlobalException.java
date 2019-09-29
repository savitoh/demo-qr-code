package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public abstract class CustomGlobalException extends RuntimeException {

    private final String messageError;

    private  final HttpStatus httpStatus;

    public CustomGlobalException(String message, HttpStatus httpStatus) {
        super(message);
        this.messageError = message;
        this.httpStatus = httpStatus;
    }

    public String getMessageError() {
        return messageError;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
