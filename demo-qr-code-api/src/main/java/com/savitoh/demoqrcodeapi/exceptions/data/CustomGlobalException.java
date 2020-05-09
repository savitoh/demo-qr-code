package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public abstract class CustomGlobalException extends RuntimeException {

    /**
	 *
	 */
    private static final long serialVersionUID = 8133098212662022841L;


    public CustomGlobalException(String message) {
        super(message);
    }

    public abstract String getMessageError();

    public abstract HttpStatus getHttpStatus();
    
}
