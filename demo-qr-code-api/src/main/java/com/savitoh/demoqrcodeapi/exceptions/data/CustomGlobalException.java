package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public class CustomGlobalException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4528443300724557507L;


    private final String messageError;

    private final HttpStatus httpStatus;

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