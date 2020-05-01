package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public class CustomGlobalClientApiException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 6847611004623607334L;

	private final String messageError;

    private final HttpStatus httpStatus;

    public CustomGlobalClientApiException(String message) {
        super(message);
        this.messageError = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public String getMessageError() {
        return messageError;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
}