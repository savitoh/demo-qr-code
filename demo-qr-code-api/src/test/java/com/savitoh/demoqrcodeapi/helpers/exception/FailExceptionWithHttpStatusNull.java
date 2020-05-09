package com.savitoh.demoqrcodeapi.helpers.exception;

import com.savitoh.demoqrcodeapi.exceptions.data.CustomGlobalException;

import org.springframework.http.HttpStatus;

public class FailExceptionWithHttpStatusNull extends CustomGlobalException {

    public FailExceptionWithHttpStatusNull(String message) {
        super(message);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessageError() {
        return super.getMessage();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
    
}