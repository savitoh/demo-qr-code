package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public abstract class CustomGlobalServerException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 8133098212662022841L;

	private final String messageError;

    private final HttpStatus httpStatus;

    public CustomGlobalServerException(String message) {
        super(message);
        this.messageError = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getMessageError() {
        return messageError;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
